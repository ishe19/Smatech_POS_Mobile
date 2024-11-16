package tech.ishe.smatechpos.views.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CartTab (){
    Column (modifier = Modifier.fillMaxSize().padding(8.dp)){
        Text(text = "Cart Tab")
    }
}