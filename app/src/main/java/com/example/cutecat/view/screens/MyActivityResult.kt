package com.example.cutecat.view.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cutecat.model.cat.CatItem

class MyActivityResult(private val activityResultRegistry: ActivityResultRegistry,
                       private val callback: (city: CatItem) -> Unit) {

    val getCity: ActivityResultLauncher<Intent> =
        activityResultRegistry.register("city1", ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == Activity.RESULT_OK) {
                callback(it.data?.getSerializableExtra("city") as CatItem)
            }
        }

    fun startActivity(context: Context){
        getCity.launch(Intent(context, FullImageActivity::class.java))
    }
}