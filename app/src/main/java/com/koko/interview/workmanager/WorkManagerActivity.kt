package com.koko.interview.workmanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.*
import com.koko.interview.ComposeApplication
import java.util.concurrent.TimeUnit

/**
 * workmanager test
 * Created by huanggang on 2022/10/24
 */
val TAG = "worker test"
class WorkManagerActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main{
                testWoker(this)

            }
        }
    }
}

class SimpleWorker(context:Context,params:WorkerParameters):Worker(context,params){
    override fun doWork(): Result {
        Log.e(TAG, "doWork:  worker test  " )

        return Result.success()
    }
}

@Composable
fun Main(onclick:()->Unit ){
    Column {
        Button(
            onClick = {
                onclick()
            },
            border = BorderStroke(1.dp, Color.Cyan),
            content = { Text(text = "workmanger")}
        )

        CounterScreen()
    }
}

class CounterViewModel:ViewModel(){
    private val _counter = mutableStateOf(0)
    val counter:State<Int> = _counter

    fun increment(){
        _counter.value ++
    }
    fun decrement (){
        _counter.value--
    }
}

@Composable
fun CounterScreen(){
    val viewModel:CounterViewModel = viewModel()
    CounterComponent(viewModel.counter.value,viewModel::increment,viewModel::decrement)
}

@Composable
fun CounterComponent(
    counter:Int = 0,
    onIncrement:() -> Unit,
    onDecrement:()-> Unit){
    Column (modifier = Modifier.padding(16.dp)){
        Text(text = "Click the buttons to adjuest your value",Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
//        key(){
//
//        }
        Text(text = "$counter", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.h3)
        Row {
            Button(
                onClick = onIncrement,
                modifier = Modifier.weight(1f),
            content = {
                Text("+")
            })

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = onDecrement,
                modifier = Modifier.weight(1f),
                content = {
                    Text("-")
                }
            )
        }

    }
}

fun testWoker(owner:LifecycleOwner){
    WorkManager.getInstance(ComposeApplication.context).cancelAllWorkByTag("onetime")
    val oneTimeWorkRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java).addTag("onetime").build()
    val oneTimeWorkRequest2 = OneTimeWorkRequestBuilder<SimpleWorker>().addTag("onetime").build()

    val periodicWorkRequest = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
    val periodicWorkRequest2 = PeriodicWorkRequestBuilder<SimpleWorker>(15,TimeUnit.MINUTES).build()

    WorkManager.getInstance(ComposeApplication.context).enqueue(listOf(oneTimeWorkRequest,oneTimeWorkRequest2,periodicWorkRequest))

    WorkManager.getInstance(ComposeApplication.context).getWorkInfosByTagLiveData("onetime").observe(owner,{
       for (workinfo in it){

           Log.e(TAG, "testWoker:  ${workinfo.id }   ${workinfo.state}   ${workinfo.tags}" )
       }
    })
}