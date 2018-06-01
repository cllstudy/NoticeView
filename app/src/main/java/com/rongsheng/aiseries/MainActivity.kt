package com.rongsheng.aiseries

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.rongsheng.aiseries.view.NoticeView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val noticeView = findViewById(R.id.notice_view) as NoticeView
        var notice: MutableList<String> = mutableListOf()
        notice.add("大促销下单拆福袋，亿万新年红包随便拿")
        notice.add("家电五折团，抢十亿无门槛现金红包")
        notice.add("星球大战剃须刀首发送200元代金券")
        noticeView.addNotice(notice)
        noticeView.startFlipping()
        noticeView.setOnNoticeClickListener(object : NoticeView.OnNoticeClickListener {
            override fun onNotieClick(position: Int, notice: String) {
             AlertDialog.Builder(this@MainActivity)
                     .setMessage(notice)
                     .show()
            }
        })

    }
}
