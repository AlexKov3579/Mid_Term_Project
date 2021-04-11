package lv.rtu.tic_tac_toe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GameEndFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_end_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.GameState).text = arguments?.getString("GameState")
        var bundle = Bundle()
        bundle.putString("PlayerName", arguments?.getString("PlayerName"))
        view.findViewById<Button>(R.id.ToMenu).setOnClickListener {
            findNavController().navigate(R.id.action_GameEndFragment_To_MainMenu, bundle)
        }
        view.findViewById<Button>(R.id.ToGameFragment).setOnClickListener {
            bundle.putString("GameMode", arguments?.getString("GameMode"))
            findNavController().navigate(R.id.action_GameEndFragment_to_GameFragment, bundle)
        }
    }
}