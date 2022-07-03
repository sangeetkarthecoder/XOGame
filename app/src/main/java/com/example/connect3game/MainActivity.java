package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //0 = red, 1 = yellow
    int active_player = 0;
    //2 = empty
    int [] game_state = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    //positions on the grid where a player wins
    int [][] winning_pos = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    //state of game
    boolean game_active = true;
    //Storing the color of winner
    String winner = "";

    public void pressed(View view) {
        ImageView v = (ImageView) view;

        //stores the value of the position of the grid which has been recently tapped
        int Tapped_counter = Integer.parseInt(v.getTag().toString());

        if(game_state[Tapped_counter]==2 && game_active) { //Validation when game ends and when a grid space is filled with a color

            //Setting that tapped position to a non empty value(0 or 1) i.e (Red or Yellow)
            game_state[Tapped_counter] = active_player;

            //sets position of the ImageView outside the display screen
            v.setY(-4000);

            if (active_player == 0) {
                v.setImageResource(R.drawable.red);
                active_player = 1;
            } else {
                v.setImageResource(R.drawable.yellow);
                active_player = 0;
            }
            //Animates the ImageView and brings it on the grid
            v.animate().translationYBy(4100).alpha(1).setDuration(300);

            //Winning logic
            for (int[] winpos : winning_pos) {

                if (game_state[winpos[0]] == game_state[winpos[1]] && game_state[winpos[1]] == game_state[winpos[2]] && game_state[winpos[0]] != 2) {

                    game_active = false;

                    if (active_player == 1) {
                        winner = "Red";
                    } else
                        winner = "Yellow";

                    TextView game_winner = findViewById(R.id.WinnerTV);
                    Button r = findViewById(R.id.PlayButton);
                    game_winner.setText(String.format("%s has won!", winner));
                    game_winner.setVisibility(View.VISIBLE);
                    r.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    
    public void play_again(View view) {
        TextView game_winner = findViewById(R.id.WinnerTV);
        Button r = findViewById(R.id.PlayButton);
        game_winner.setVisibility(View.INVISIBLE);
        r.setVisibility(View.INVISIBLE);
        this.recreate();
    }
}