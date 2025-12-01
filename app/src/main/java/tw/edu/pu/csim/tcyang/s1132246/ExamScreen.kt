package tw.edu.pu.csim.tcyang.s1132246

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun ExamScreen(vm: ExamViewModel = viewModel()) {

    // 取得螢幕寬高
    val config = LocalConfiguration.current
    val screenWidthPx = config.screenWidthDp * config.densityDpi / 160
    val screenHeightPx = config.screenHeightDp * config.densityDpi / 160

    // 讓 ViewModel 更新螢幕資訊
    LaunchedEffect(Unit) {
        vm.updateScreenSize(screenWidthPx, screenHeightPx)
    }

    // 黃色背景
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {

        // ⭐ 第二題：中間大圖片 + 文字
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 中間大圖（請確認有 exam.png 或替換）
            Image(
                painter = painterResource(id = R.drawable.happy),   // ← 換成中間大圖
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "系級：資管三A / 姓名：沈博雅",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "螢幕大小：${vm.screenWidth}px × ${vm.screenHeight}px",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "成績：${vm.score}",
                style = MaterialTheme.typography.titleMedium
            )
        }


        // ⭐ 第三題：四角角色圖示 (300px = 100dp)

        val halfScreen = (config.screenHeightDp.dp / 2) - 100.dp   // 上排切齊螢幕一半

        // 嬰幼兒（左上）
        Image(
            painter = painterResource(id = R.drawable.role0),
            contentDescription = "嬰幼兒",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopStart)
                .offset(y = halfScreen)
        )

        // 兒童（右上）
        Image(
            painter = painterResource(id = R.drawable.role1),
            contentDescription = "兒童",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
                .offset(y = halfScreen)
        )

        // 成人（左下）
        Image(
            painter = painterResource(id = R.drawable.role2),
            contentDescription = "成人",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
        )

        // 一般民眾（右下）
        Image(
            painter = painterResource(id = R.drawable.role3),
            contentDescription = "一般民眾",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

