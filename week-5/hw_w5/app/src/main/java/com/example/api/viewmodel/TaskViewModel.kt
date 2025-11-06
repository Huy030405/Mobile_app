package com.example.api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.Task
import com.example.api.data.network.RetrofitClient
import kotlinx.coroutines.launch
import android.util.Log

// ViewModel là tầng trung gian (Bồi Bàn)
// Nó không biết gì về UI (Compose) và chỉ nói chuyện với "Nhà Bếp" (Data layer)
class TaskViewModel : ViewModel() {

    init {
        Log.d("TaskViewModel", "--- VIEWMODEL ĐÃ ĐƯỢC TẠO MỚI (RE-CREATED) ---")
    }

    private var daGoiApiChua = false

    // Lấy "máy pha cà phê" (Retrofit client) từ nhà bếp
    private val apiService = RetrofitClient.instance

    // --- Các "khay" chứa đồ ăn (State) ---
    // MutableLiveData là "khay có thể thay đổi" (chỉ ViewModel được sửa)
    // LiveData là "khay chỉ được nhìn" (UI chỉ được xem)

    // Khay chứa danh sách tasks
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    // Khay chứa chi tiết 1 task
    private val _taskDetail = MutableLiveData<Task>()
    val taskDetail: LiveData<Task> = _taskDetail

    // Khay báo "Đang chạy đi lấy..." (Loading)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Khay báo "Danh sách rỗng" (EmptyView)
    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    // Khay báo "Đã xoá thành công"
    private val _deleteSuccess = MutableLiveData<Boolean>(false) // Khởi tạo là false
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess

    // --- Các hành động (Logic) ---

    // Hành động: "Lấy tất cả tasks"
    fun fetchAllTasks() {
        if (daGoiApiChua) return
        _isLoading.value = true // Báo: "Đang load..."
        _isEmpty.value = false  // Reset

        // viewModelScope tự động chạy trên luồng nền (background thread)
        viewModelScope.launch {
            try {
                val response = apiService.getAllTasks() // Lấy "cái hộp"
                val taskList = response.taskList       // Mở hộp lấy danh sách

                _tasks.value = taskList
                _isEmpty.value = taskList.isEmpty()
                Log.d("TaskViewModel", "API Success. Data count: ${taskList.size}")
                daGoiApiChua = true

            } catch (e: Exception) {
                // Xử lý lỗi (ví dụ: mất mạng)
                _tasks.value = emptyList() // Đặt khay rỗng
                _isEmpty.value = true      // Báo là rỗng
                Log.e("TaskViewModel", "API Error", e)
            } finally {
                _isLoading.value = false // Báo: "Load xong rồi!"
            }
        }
    }

    // Hành động: "Lấy chi tiết 1 task"
    fun fetchTaskDetail(taskId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val detail = apiService.getTaskDetail(taskId)
                _taskDetail.value = detail // Đặt lên khay chi tiết
            } catch (e: Exception) {
                // Xử lý lỗi
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Hành động: "Xoá 1 task"
    fun deleteTask(taskId: String) {

        // 1. LỌC DANH SÁCH NGAY LẬP TỨC (Optimistic UI)
        // Chúng ta không đợi API trả lời nữa
        val currentList = _tasks.value ?: emptyList()
        val updatedList = currentList.filter { task ->
            task.id != taskId
        }

        // 2. CẬP NHẬT "KHAY" (LiveData) NGAY LẬP TỨC
        _tasks.value = updatedList
        _isEmpty.value = updatedList.isEmpty()

        // 3. BÁO CHO UI BIẾT LÀ ĐÃ XONG (ĐỂ NÓ QUAY VỀ)
        _deleteSuccess.value = true

        // 4. BÂY GIỜ MỚI GỌI API (chỉ để "cho có" theo yêu cầu slide)
        // Chúng ta không cần "try-catch" ở đây nữa
        viewModelScope.launch {
            try {
                // "Bắn và quên" (Fire and forget)
                apiService.deleteTask(taskId)
                Log.d("TaskViewModel", "Delete API call sent (Optimistic) for $taskId")
            } catch (e: Exception) {
                // API bị lỗi cũng kệ, vì UI đã cập nhật rồi
                Log.e("TaskViewModel", "Delete API Error (Ignored)", e)
            }
        }
    }
    fun resetDeleteSuccessFlag() {
        _deleteSuccess.value = false
    }
}