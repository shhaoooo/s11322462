package tw.edu.pu.csim.tcyang.s1132246

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ExamViewModel : ViewModel() {

    // 第二題
    var screenWidth = mutableStateOf(0)      // px
    var screenHeight = mutableStateOf(0)     // px
    var score = mutableStateOf(0)

    // 第四題：服務圖位置（px）
    var serviceX = mutableStateOf(0f)
    var serviceY = mutableStateOf(0f)

    // 第四題：目前服務圖 index
    var currentService = mutableStateOf(0)

    private var dropJob: Job? = null

    /** 每次更新螢幕尺寸 → 重置服務圖＋開始掉落 */
    fun updateScreenSize(width: Int, height: Int) {
        screenWidth.value = width
        screenHeight.value = height

        resetService()
        startDrop()
    }

    /** 隨機生成服務圖 （位置用 px）*/
    fun resetService() {

        currentService.value = Random.nextInt(0, 4)

        // 將 serviceX 置中（px）
        serviceX.value = (screenWidth.value / 2f) - 50f   // 圖示 100px 寬 → 左邊要 -50

        serviceY.value = 0f
    }

    /** 每 0.1 秒下降 20px */
    fun startDrop() {
        dropJob?.cancel()

        dropJob = MainScope().launch {
            while (true) {
                delay(100)
                serviceY.value += 20        // 掉落 20px

                // 到達底部（px）
                if (serviceY.value >= screenHeight.value - 100) {
                    resetService()
                }
            }
        }
    }

    /** 左右拖曳（px） */
    fun moveService(deltaX: Float) {
        serviceX.value += deltaX
    }
}
