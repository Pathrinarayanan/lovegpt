package com.example.lovegpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lovegpt.ui.theme.LoveGptTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewmodel: MainViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewmodel::class)
        enableEdgeToEdge()
        setContent {
            LoveGptTheme {
                    MainScreen(viewmodel)

            }
        }
    }
}

@Composable
fun MainScreen(viewmodel: MainViewmodel){
    val gradient = Brush
        .linearGradient(
            listOf(
                Color(0xffFFDDF7),
                Color(0xffC5ECFF),
                Color(0xffDEE9FF),
                Color(0xffDEE9FF),
            )
        )
    val textGradient = Brush
        .linearGradient(
            listOf(
                Color(0xff2F0EFD),
                Color(0xffCE30D1),
            )
        )
    Scaffold(
        Modifier,
        topBar = {
            Row (modifier = Modifier.padding(top = 40.dp).padding(16.dp), verticalAlignment = Alignment.CenterVertically){
                Spacer(Modifier.weight(1f))
                Box (
                    modifier = Modifier.background(Color.White, RoundedCornerShape(16.dp)).padding(16.dp)
                ){
                    Text("Love GPT",Modifier, fontWeight = FontWeight.W600, fontSize = 20.sp)
                }
                Spacer(Modifier.weight(1f))
                Box(Modifier.background(Color.White, RoundedCornerShape(12.dp)).padding(5.dp)){
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            }
        },
        bottomBar = {
            Row(
                Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(8.dp,RoundedCornerShape(16.dp))
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                TextField(
                    value = viewmodel.text,
                    onValueChange = {
                        viewmodel.text = it
                    },
                    Modifier,
                    placeholder = {
                        Text("Hi, I am your AI companion")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
            }
                Box(
                    Modifier
                        .background(Color.White, CircleShape)
                        .padding(16.dp)
                        .clickable{
                            viewmodel.fetchData()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = null,
                        Modifier.size(25.dp),
                        tint = Color(0xffCE30D1)
                    )
                }
            }


        }
    ) {
        if (viewmodel.responses.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()
            .background(gradient), contentAlignment = Alignment.Center) {

                Box(
                    Modifier
                        .background(Color.White, CircleShape).padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.love_bot),
                        contentDescription = null,
                        Modifier.size(150.dp)
                    )
                }
            }
        }
        else{
            LazyColumn  (
                modifier = Modifier.fillMaxSize()
                    .background(gradient)
                    .padding(it)
                    .padding(16.dp),
                reverseLayout = true
            ) {
                itemsIndexed(viewmodel.responses.value) {index, data->
                    if(data?.isFromUser==false) {
                        Text(
                            data.message ?: "",
                            Modifier
                                .padding(vertical = 10.dp)
                                .background(textGradient, RoundedCornerShape(16.dp))
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            color = Color.White
                        )
                    }else {
                        Text(
                            data?.message ?:"",
                            Modifier
                                .padding(vertical = 10.dp)
                                .background(Color.White, RoundedCornerShape(16.dp))
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            color = Color.Black
                        )
                    }
                }
            }
    }
    }
}

data class MessageType(
    val message : String?,
    val isFromUser : Boolean
)

