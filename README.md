# Android 带清除功能的输入框控件EditTextWithDel
记录下一个很实用的小控件EditTextWithDel，就是在Android系统的输入框右边加入一个小图标，点击小图标可以清除输入框里面的内容，由于Android原生EditText不具备此功能，所以要想实现这一功能我们需要重写EditText。

效果图如下：

![](http://img.blog.csdn.net/20160229000515271)

主要的思路就是为右边的图片设置监听，点击右边的图片清除输入框的内容并隐藏删除图标,因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件,用输入框的的onTouchEvent()方法来模拟.

```Java
package com.xiaolijuan.edittextwithdeldome;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * @author: adan
 * @description: 自定义带有删除功能的EditText
 * @projectName: EditTextWithDelDome
 * @date: 2016-02-28
 * @time: 23:34
 */
public class EditTextWithDel extends EditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Drawable imgAble;
    private Context mContext;

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }
    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        imgAble = mContext.getResources().getDrawable(
                R.mipmap.icon_delete_gray);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }

    // 设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
```
setDrawable()方法，setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)来在上、下、左、右设置图标，如果不想在某个地方显示，则设置为null。 
