package com.example.zad1_5

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var tail: Int = 0
    private var body: Int = 0
    private var head: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeGame()
        val downBtn = findViewById<Button>(R.id.downBtn)
        val leftBtn = findViewById<Button>(R.id.leftBtn)
        val rightBtn = findViewById<Button>(R.id.rightBtn)
        val upBtn = findViewById<Button>(R.id.upBtn)
        val gamePanel = findViewById<GridLayout>(R.id.gamePanel)
        val childCount = gamePanel.childCount

        upBtn.setOnClickListener {
            var collision = false
            if(this.body-(childCount-6)==this.head){
                collision = true
            }else if(this.body+6==this.head){
                collision = true
            }
            if(!collision){
                updateSnake(gamePanel)

                if (this.head-gamePanel.rowCount<0) this.head = this.head-gamePanel.rowCount+childCount
                else this.head = this.head-gamePanel.rowCount
                (gamePanel.getChildAt(this.head) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)
            }
        }

        downBtn.setOnClickListener {
            var collision = false
            if(this.body+childCount-6==this.head){
                collision = true
            }else if(this.body-6==this.head){
                collision = true
            }
            if(!collision){
                updateSnake(gamePanel)

                if (this.head+gamePanel.rowCount>=childCount) this.head = this.head+gamePanel.rowCount-childCount
                else this.head = this.head+gamePanel.rowCount
                (gamePanel.getChildAt(this.head) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)
            }
        }

        leftBtn.setOnClickListener {
            var collision = false
            if(this.body%gamePanel.columnCount==5 && this.head%gamePanel.columnCount==0){
                collision = true
            }else if(this.body+1==this.head){
                collision = true
            }
            if(!collision) {
                updateSnake(gamePanel)

                if (this.head % gamePanel.columnCount == 0) this.head = this.head + (gamePanel.columnCount - 1)
                else this.head = this.head - 1
                (gamePanel.getChildAt(this.head) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)
            }
        }

        rightBtn.setOnClickListener {
            var collision = false
            if(this.body%gamePanel.columnCount==0 && this.head%gamePanel.columnCount==5){
                collision = true
            }else if(this.body-1==this.head){
                collision = true
            }
            if(!collision){
                updateSnake(gamePanel)

                if (this.head%gamePanel.columnCount==gamePanel.columnCount-1) this.head = this.head-(gamePanel.columnCount-1)
                else this.head = this.head+1
                (gamePanel.getChildAt(this.head) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)
            }
        }
    }

    private fun initializeGame() {
        val textView1 = findViewById<TextView>(R.id.textView1)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView2.setBackgroundColor(Color.RED)
        textView1.setBackgroundColor(Color.RED)
        textView3.setBackgroundColor(Color.RED)
        this.tail = 0
        this.body = 1
        this.head = 2
    }

    private fun updateSnake(gamePanel: GridLayout) {
        (gamePanel.getChildAt(this.tail) as LinearLayout).getChildAt(0).setBackgroundColor(Color.rgb(66,198,36))

        this.tail = this.body
        (gamePanel.getChildAt(this.tail) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)

        this.body = this.head
        (gamePanel.getChildAt(this.body) as LinearLayout).getChildAt(0).setBackgroundColor(Color.RED)
    }

}