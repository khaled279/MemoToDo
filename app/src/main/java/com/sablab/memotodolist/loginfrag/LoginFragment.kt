package com.sablab.memotodolist.loginfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.sablab.memotodolist.R

class LoginFragment:Fragment() {
    private val id:String ="khaled_sabri"
    private val password ="password"
    private var  loginBtn:Button? = null
    private var loginEditText:EditText? = null
    private var loginPassword:EditText? =null
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login,container,false)
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBtn = view.findViewById(R.id.button_login)
        loginEditText = view.findViewById(R.id.editetxt_user_name2)
        loginPassword = view.findViewById(R.id.editetxt_password)
        loginBtn?.setOnClickListener {

                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_tasksFragment)

        }

    }


}
