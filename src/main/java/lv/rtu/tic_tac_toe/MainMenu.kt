package lv.rtu.tic_tac_toe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MainMenu : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_menu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val argName:String? = arguments?.getString("PlayerName")
        val text:String = "Hi, $argName"
        view.findViewById<TextView>(R.id.PlayerName).text = text
        val bundle:Bundle = Bundle()
        bundle.putString("PlayerName", argName)
        view.findViewById<Button>(R.id.SinglePlayer).setOnClickListener {
            bundle.putString("GameMode", "SinglePlayer")
            findNavController().navigate(R.id.action_MainMenu_To_GameFragment, bundle)
        }
        view.findViewById<Button>(R.id.MultiPlayer).setOnClickListener {
            bundle.putString("GameMode", "MultiPlayer")
            findNavController().navigate(R.id.action_MainMenu_To_GameFragment, bundle)
        }
    }
}