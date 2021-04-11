package lv.rtu.tic_tac_toe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HelloMenu : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hello_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.TO_MAIN_MENU).setOnClickListener {
            val inputField : TextInputEditText = view.findViewById(R.id.InputText)
            val playerName : String = inputField.text.toString()
            if(playerName.length<1){
                view.findViewById<TextView>(R.id.WarningText).text =
                    getString(R.string.Warning_name_length)
            }
            else {
                val bundle = Bundle()
                bundle.putString("PlayerName", playerName)
                findNavController().navigate(R.id.action_HelloMenu_To_MainMenu, bundle)
            }
        }
    }
}