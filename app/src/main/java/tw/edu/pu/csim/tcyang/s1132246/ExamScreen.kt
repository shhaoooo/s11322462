package tw.edu.pu.csim.tcyang.s1132246

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.absoluteOffset   // ✔ 正確
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExamScreen(vm: ExamViewModel = viewModel()) {

    val config = LocalConfiguration.current

    // px
    val screenWidthPx = (config.screenWidthDp * config.densityDpi / 160)
    val screenHeightPx = (config.screenHeightDp * config.densityDpi / 160)

    LaunchedEffect(Unit) {
        vm.updateScreenSize(screenWidthPx, screenHeightPx)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {

        // ⭐ 第二題：中間圖片與文字
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "系級：資管三A / 姓名：沈博雅",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "螢幕大小：${vm.screenWidth.value}px × ${vm.screenHeight.value}px",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "成績：${vm.score.value}",
                style = MaterialTheme.typography.titleMedium
            )
        }

        // ⭐ 第三題：四角人物
        val topOffset = (config.screenHeightDp.dp / 2) - 100.dp

        Image(
            painter = painterResource(R.drawable.role0),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopStart)
                .offset(y = topOffset)
        )

        Image(
            painter = painterResource(R.drawable.role1),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
                .offset(y = topOffset)
        )

        Image(
            painter = painterResource(R.drawable.role2),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
        )

        Image(
            painter = painterResource(R.drawable.role3),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
        )

        // ⭐⭐⭐⭐⭐ 第四題：掉落 + 拖曳的服務圖片 ⭐⭐⭐⭐⭐

        val serviceImgs = listOf(
            R.drawable.service0,
            R.drawable.service1,
            R.drawable.service2,
            R.drawable.service3
        )

        val currentImg = serviceImgs[vm.currentService.value]

        val dragModifier = Modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                vm.moveService(dragAmount.x)
            }
        }

        Image(
            painter = painterResource(currentImg),
            contentDescription = "服務圖示",
            modifier = dragModifier
                .size(100.dp)
                .absoluteOffset(
                    x = (vm.serviceX.value / config.densityDpi * 160).dp,
                    y = (vm.serviceY.value / config.densityDpi * 160).dp
                )

        )
    }
}
