package com.clant.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Window
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clant.jetpackdemo.databind.UserBean
import com.clant.jetpackdemo.databind.UserBean_OneWay
import com.clant.jetpackdemo.databinding.CustomBindingName
import com.clant.jetpackdemo.lifecycle.MainActivityLifecycle
import com.clant.jetpackdemo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //viewBind初始化
    lateinit var binding: CustomBindingName //CustomBindingName为在xml文件中自定义的名称

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = CustomBindingName.inflate(layoutInflater)
        setContentView(binding.root)
        //lifecycle初始化
        lifecycle.addObserver(MainActivityLifecycle())

        //viewmodel初始化
        mainViewModel = ViewModelProvider(
            viewModelStore,
            //当前activity作用域，如果当前activity重新创建，则也会新建viewmodel
            //ViewModelProvider.NewInstanceFactory()
            //用application作用域
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(MainViewModel::class.java)

        btn_add.setOnClickListener {
            //调用viewmodel中的变量
            mainViewModel.mCount++
            binding.tvShowCount.setText(mainViewModel.mCount.toString())
            //通过LiveData传值
            mainViewModel.viewLiveDataValue.postValue("post-->>加一了")
        }
        btn_red.setOnClickListener {
            //调用viewmodel中的变量
            mainViewModel.mCount--
            binding.tvShowCount.text = mainViewModel.mCount.toString()
            //通过LiveData传值
            mainViewModel.viewLiveDataValue.postValue("post-->>减一了")
        }


//        mainViewModel.viewLiveData.value = ""
        mainViewModel.viewLiveDataValue.observe(this, Observer {
            Log.i("fxy", "livedata_postvalue:||" + it.toString())
        })

        //databinding使用
        var userBean = UserBean("张三", 22, "男")
        binding.userBean = userBean

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                userBean.name = p0.toString()
                binding.userBean = userBean
                Log.i("fxy", "" + p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        et_age.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                userBean.age = p0.toString().toInt()
                binding.userBean = userBean
                Log.i("fxy", "" + p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        et_sex.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                userBean.sex = p0.toString()
                binding.userBean = userBean
                Log.i("fxy", "" + p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        //单向绑定使用
        var userBean2 = UserBean_OneWay()
        binding.userBean2 = userBean2
        btn_on_way_binding.setOnClickListener {
            //单向绑定实现
            userBean2.name = "单向绑定"
            //通过ObservableField实现具体看UserBean_OneWay中代码
            userBean2.age.set(11)
            userBean2.sex.set("女")
        }

        //双向绑定
        btn_two_way_binding.setOnClickListener {
            //单向绑定实现
            userBean2.name = "双向绑定"
            //通过ObservableField实现具体看UserBean_OneWay中代码
            userBean2.age.set(22)
            userBean2.sex.set("男")
        }

    }

}