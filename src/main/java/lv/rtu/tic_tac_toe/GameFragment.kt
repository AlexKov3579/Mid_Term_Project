package lv.rtu.tic_tac_toe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController

class GameFragment: Fragment() {
    private val gameField: GameField = GameField()
    private val player1 :Player = Player("", 'X')
    private val player2 :Player = Player("Guest Player", 'O')
    private var activePlayer:Player = player1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_fragment, container, false)
    }
// idea for logic comes from https://codinginflow.com/tutorials/android/tic-tac-toe/part-2-winning-logic
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argName: String = arguments?.getString("PlayerName")!!
        val argGameMode: String = arguments?.getString("GameMode")!!
        player1.setPlayerName(argName)

        var bundle:Bundle = Bundle()
        bundle.putString("PlayerName", argName)
        bundle.putString("GameMode", argGameMode)
        for (i in 0..2)
            for(j in 0..2){
                val buttonId:String = "button$i$j"
                val id:Int = resources.getIdentifier(buttonId, "id", "lv.rtu.tic_tac_toe")
                val button:Button =  view.findViewById<Button>(id)
                if(argGameMode == "MultiPlayer"){
                    // lines below mostly copies, have no time to extract them as function
                    button.setOnClickListener{
                        if (gameField.setVal(i, j, activePlayer)){
                            button.text = activePlayer.getPlayerSymbol().toString()
                            if(gameField.checkForWin(activePlayer)){
                                bundle.putString("GameState", "${activePlayer.getPlayerName()} has won!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                            else if(gameField.checkForDraw()) {
                                bundle.putString("GameState", "Draw!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                            activePlayer = if(activePlayer == player1) player2 else player1
                        }

                        view.findViewById<TextView>(R.id.ActivePlayerLabel).text = activePlayer.getPlayerName()
                    }
                } else {
                    button.setOnClickListener{
                        view.findViewById<TextView>(R.id.ActivePlayerLabel).text = player1.getPlayerName()
                        if (gameField.setVal(i, j, player1)){
                            button.text = player1.getPlayerSymbol().toString()
                            if(gameField.checkForWin(player1)){
                                bundle.putString("GameState", "${player1.getPlayerName()} has won!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                            else if(gameField.checkForDraw()) {
                                bundle.putString("GameState", "Draw!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                            // 'AI' turn
                            val coords = gameField.setRandomVal(player2)
                            val aiTurnPressedButtonId:Int = resources.getIdentifier("button$coords", "id", "lv.rtu.tic_tac_toe")
                            view.findViewById<Button>(aiTurnPressedButtonId).text = player2.getPlayerSymbol().toString()
                            if(gameField.checkForWin(player2)){
                                bundle.putString("GameState", "${player2.getPlayerName()} has won!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                            else if(gameField.checkForDraw()) {
                                bundle.putString("GameState", "Draw!")
                                findNavController().navigate(R.id.action_GameFragment_to_GameEndFragment, bundle)
                            }
                        }


                    }

                }

            }
    }
}