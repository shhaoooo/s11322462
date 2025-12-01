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
            .background(Color(0xFFFFFF00)), // 黃色
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 圖片（請確認 drawable 裡有 exam_img.png 或換成你要的）
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ✏️ 修改成你的系級與姓名
            Text(
                text = "系級：資管二A / 姓名:洪唯皓",
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
    }
}
