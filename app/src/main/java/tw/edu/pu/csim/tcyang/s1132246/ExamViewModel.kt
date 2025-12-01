package tw.edu.pu.csim.tcyang.s1132246

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExamViewModel : ViewModel() {

    var screenWidth by mutableStateOf(0)
    var screenHeight by mutableStateOf(0)
    var score by mutableStateOf(0)

    fun updateScreenSize(width: Int, height: Int) {
        screenWidth = width
        screenHeight = height
    }
}