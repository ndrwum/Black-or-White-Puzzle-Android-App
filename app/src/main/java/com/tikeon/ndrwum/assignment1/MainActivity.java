package com.tikeon.ndrwum.assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Board board;
    Button[] buttons;

    int[] switchA = {-1, -1, -1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    int[] switchB = {1, 1, 1, -1,
            1, 1, 1, -1,
            1, -1, 1, -1,
            1, 1, 1, 1};
    int[] switchC = {1, 1, 1, 1,
            -1, 1, 1, 1,
            1, 1, -1, 1,
            1, 1, -1, -1};
    int[] switchD = {-1, 1, 1, 1,
            -1, -1, -1, -1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    int[] switchE = {1, 1, 1, 1,
            1, 1, -1, -1,
            -1, 1, -1, 1,
            -1, 1, 1, 1};
    int[] switchF = {-1, 1, -1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    int[] switchG = {1, 1, 1, -1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    int[] switchH = {1, 1, 1, 1,
            -1, -1, 1, -1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    int[] switchI = {1, -1, -1, -1,
            -1, -1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    int[] switchJ = {1, 1, 1, -1,
            -1, -1, 1, 1,
            1, -1, 1, 1,
            1, -1, 1, 1};
    int[] switchTest = {1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = new Board();
        setBoard();
        setMoveCount();
        setSequence();

    }

    public void setBoard() {

        buttons = new Button[]{
                (Button) findViewById(R.id.sq0), (Button) findViewById(R.id.sq1),
                (Button) findViewById(R.id.sq2), (Button) findViewById(R.id.sq3),
                (Button) findViewById(R.id.sq4), (Button) findViewById(R.id.sq5),
                (Button) findViewById(R.id.sq6), (Button) findViewById(R.id.sq7),
                (Button) findViewById(R.id.sq8), (Button) findViewById(R.id.sq9),
                (Button) findViewById(R.id.sq10), (Button) findViewById(R.id.sq11),
                (Button) findViewById(R.id.sq12), (Button) findViewById(R.id.sq13),
                (Button) findViewById(R.id.sq14), (Button) findViewById(R.id.sq15)
        };

        int[] squares = board.getAssignments();

        assignSqrColors(squares);
    }

    public void assignSqrColors(int[] squares) {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        int darkGray = ContextCompat.getColor(getApplicationContext(), R.color.dark_gray);
        for (int i = 0; i < 16; i++) {
            if (squares[i] == 1) {
                buttons[i].setBackgroundColor(white);
            } else
                buttons[i].setBackgroundColor(darkGray);
        }

        if (board.isSolved(board.getAssignments())) {
            SpannableStringBuilder biggerText = new SpannableStringBuilder("You Win!");
            biggerText.setSpan(new RelativeSizeSpan(3.35f), 0, "You Win!".length(), 0);
            Toast.makeText(MainActivity.this, biggerText, Toast.LENGTH_LONG).show();
        }
    }

    public void resetBoard(View v) {
        board = new Board();
        setMoveCount();
        setSequence();
        setBoard();
    }

    public void setMoveCount() {
        TextView move_count = (TextView) findViewById(R.id.move_count);
        move_count.setText("Move Count: " + board.getMoveCount());
    }

    public void setSequence() {
        TextView sequence = (TextView) findViewById(R.id.sequence);
        sequence.setText("Sequence: " + board.getSequence());
    }

    public void pressSwitch(View v) {
        switch (v.getId()) {
            case (R.id.swA):
                board.trackSeq("A");
                board.applySwitch(switchA);
                break;
            case (R.id.swB):
                board.trackSeq("B");
                board.applySwitch(switchB);
                break;
            case (R.id.swC):
                board.trackSeq("C");
                board.applySwitch(switchC);
                break;
            case (R.id.swD):
                board.trackSeq("D");
                board.applySwitch(switchD);
                break;
            case (R.id.swE):
                board.trackSeq("E");
                board.applySwitch(switchE);
                break;
            case (R.id.swF):
                board.trackSeq("F");
                board.applySwitch(switchF);
                break;
            case (R.id.swG):
                board.trackSeq("G");
                board.applySwitch(switchG);
                break;
            case (R.id.swH):
                board.trackSeq("H");
                board.applySwitch(switchH);
                break;
            case (R.id.swI):
                board.trackSeq("I");
                board.applySwitch(switchI);
                break;
            case (R.id.swJ):
                board.trackSeq("J");
                board.applySwitch(switchJ);
                break;
            case (R.id.swTest):
                board.trackSeq("Test");
                board.applySwitch(switchTest);
                test(v);
                break;
        }
        setMoveCount();
        assignSqrColors(board.getAssignments());
        setSequence();


    }

    public void findSol(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setMessage(board.findSolution());
        alertDialog.show();

    }

    public void test(View v) {
        board.testBoard();
    }
}
