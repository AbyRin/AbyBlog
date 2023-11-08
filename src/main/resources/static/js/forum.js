document.addEventListener('DOMContentLoaded', function() {
    // 遍历所有 filter 并 绑定事件
    const allFilters = document.querySelectorAll(".filter");
    let clickedFilter = null;

    allFilters.forEach(filter => {
        filter.addEventListener('mouseover', function() {
            if (filter !== clickedFilter) {
                filter.style.backgroundColor = '#E8E8E8';
            }
        });

        filter.addEventListener('mouseout', function() {
            if (filter !== clickedFilter) {
                filter.style.backgroundColor = '#ffffff';
            }
        });

        filter.addEventListener('click', function() {
            if (clickedFilter) {
                // 移除上一个点击的 filter 样式
                // clickedFilter.style.fontWeight = 'normal';
                clickedFilter.style.color = '#31384f';
                clickedFilter.style.backgroundColor = '#ffffff';
            }
            // 添加样式到点击的 filter
            // filter.style.fontWeight = 'bold';
            filter.style.color = '#ffffff';
            filter.style.backgroundColor = '#4e9da9';

            // 更新 clickedFilter
            clickedFilter = filter;
        });
    });


    // 子区1：切换按钮：显示/隐藏
    // const commentContainer = document.querySelector("comment_container");
    // const toggle = document.querySelector("toggle_bar");

    // let isHidden = true; // 初始时隐藏

    // toggle.addEventListener("click", () => {
    // if (isHidden) {
    //     commentContainer.style.bottom = "20px"; // 点击显示
    // } else {
    //     commentContainer.style.bottom = "0px"; // 点击隐藏
    // }
    // isHidden = !isHidden; // 切换状态
    // });

    // 子区2：文本  
    const text = document.getElementById('text');
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
        total.innerHTML = `${text.value.length}/400字`;
    })

    // 动态调整textarea的高度以适应内容
    const textArea = document.getElementById("text");
    const textBar = document.querySelector(".text_bar");
    const commentContainer = document.querySelector(".comment_container");
    
    textArea.addEventListener("input", () => {
        textArea.style.height = "auto"; // 重置高度
        textArea.style.height = (textArea.scrollHeight) + "px"; // 设置高度为内容的实际高度
        
        // textArea达到最大高度，textBar和commentContainer高度不再随之变化
        const maxHeight = 128;
        if (textArea.scrollHeight <= maxHeight) {
            textBar.style.height = (textArea.scrollHeight + 18) + "px"; // 适当调整text_bar的高度
            commentContainer.style.height = (textArea.scrollHeight + 92) + "px"; // 调整comment_container的高度
        } else {
            textBar.style.height = (maxHeight + 18) + "px";
            commentContainer.style.height = (maxHeight + 92) + "px";
        }
    });
    

    // // 仅在按下 回车键时 发布评论（使用结构: list）
    // text.addEventListener('keyup', function(e) {
    //     if (e.key === 'Enter') {
    //         // 若用户输入不为空，就显示和打印
    //         if (text.value.trim() !== '') {
    //         // if (text.value.trim()) {  //另一种写法
    //             item.style.display = 'block';
    //             text.innerHTML = text.value;  // 用户输入内容
    //         }
    //         // 按下回车键，清空文本域
    //         text.value = ''  // Tip: trim()方法，去除字符串前后的空格
    //         // 按下回车后，字符统计复原
    //         total.innerHTML = '0/400字'
    //     }
    // })
});