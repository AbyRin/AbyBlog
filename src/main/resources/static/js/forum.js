document.addEventListener('DOMContentLoaded', function() {
    // 功能模块：话题筛选
    const filter = document.querySelector('.filter');
    filter.addEventListener('mouseover', function() {
        filter.style.backgroundColor = '#E8E8E8';
    })
    filter.addEventListener('mouseout', function() {
        filter.style.backgroundColor = '#ffffff';
    })
    filter.addEventListener('click', function() {
        filter.style.fontWeight = 'bold';
        filter.style.backgroundColor = '#E8E8E8';
    })


    // 子区1：切换按钮：显示/隐藏
    const commentContainer = document.querySelector("comment_container");
    const toggle = document.querySelector("toggle_bar");

    let isHidden = true; // 初始时隐藏

    toggle.addEventListener("click", () => {
    if (isHidden) {
        commentContainer.style.bottom = "20px"; // 点击显示
    } else {
        commentContainer.style.bottom = "0px"; // 点击隐藏
    }
    isHidden = !isHidden; // 切换状态
    });

    // 子区2：文本  
    const text = document.querySelector('#text');
    const total = document.querySelector('.total');

    // 文本域获得焦点时, total 显示
    text.addEventListener('focus', function() {
        total.style.opacity = 1;
    })
    // 文本域失去焦点时，total 隐藏
    text.addEventListener('blur', function() {
        total.style.opacity = 0;
    })
    // 字数统计
    text.addEventListener('input', function() {
        // console.log(text.value.length);
        total.innerHTML = `${text.value.length}/200字`;
    })
    // 仅在按下 回车键时 发布评论（使用结构: list）
    text.addEventListener('keyup', function(e) {
        if (e.key === 'Enter') {
            // 若用户输入不为空，就显示和打印
            if (text.value.trim() !== '') {
            // if (text.value.trim()) {  //另一种写法
                item.style.display = 'block';
                text.innerHTML = text.value;  // 用户输入内容
            }
            // 按下回车键，清空文本域
            text.value = ''  // Tip: trim()方法，去除字符串前后的空格
            // 按下回车后，字符统计复原
            total.innerHTML = '0/200字'
        }
    })
});