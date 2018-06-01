package com.rongsheng.aiseries.view

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper

import com.rongsheng.aiseries.R

/**
 * @author : lei
 * @desc :
 * @date : 2018/6/1 0001  上午 8:58.
 * 个人博客站: http://www.bestlei.top
 */

class NoticeView : ViewFlipper, View.OnClickListener {
    private var mContext: Context? = null
    private var mNotices: List<String>? = null
    private var mOnNoticeClickListener: OnNoticeClickListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        mContext = context
        // 轮播间隔时间为3s
        setFlipInterval(3000)
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f))
        // 设置enter和leave动画
        inAnimation = AnimationUtils.loadAnimation(mContext, R.anim.notice_in)
        outAnimation = AnimationUtils.loadAnimation(mContext, R.anim.notice_out)
    }

    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
   fun addNotice(notices: List<String>) {
        mNotices = notices
        removeAllViews()
        for (i in mNotices!!.indices) {
            // 根据公告内容构建一个TextView
            val notice = notices[i]
            val textView = TextView(mContext)
            textView.setSingleLine()
            textView.text = notice
            textView.textSize = 20f
            textView.ellipsize = TextUtils.TruncateAt.END
            textView.setTextColor(Color.parseColor("#666666"))
            textView.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.tag = i
            textView.setOnClickListener(this)
            // 添加到ViewFlipper
            this@NoticeView.addView(textView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
    }


    override fun onClick(v: View) {
        val position = v.tag as Int
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener!!.onNotieClick(position, mNotices!![position])
        }
    }

    /**
     * 通知点击监听接口
     */
    interface OnNoticeClickListener {
        fun onNotieClick(position: Int, notice: String)
    }

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    fun setOnNoticeClickListener(onNoticeClickListener: OnNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener
    }

    private fun dp2px(dpValue: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            mContext!!.resources.displayMetrics).toInt()
}