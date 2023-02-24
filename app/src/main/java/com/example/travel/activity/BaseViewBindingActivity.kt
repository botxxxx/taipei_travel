package com.example.travel.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.travel.callback.BaseViewInterface

/**
 * Created by Ricky on 2021/10/13.<br/>
 * this is base view binding activity<br/>
 * if want use view binding, extends this class please
 */
abstract class DBSBaseViewBindingActivity<B : ViewBinding> : BaseCompatActivity(), BaseViewInterface {
    lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): B

    override fun getLifeCycleScope(): LifecycleCoroutineScope = lifecycleScope

    override fun getContext(): Context? = this

    override fun getRootView(): View? = binding.root
}