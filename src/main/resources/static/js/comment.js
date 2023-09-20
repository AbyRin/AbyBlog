// 获取元素
const comment_top = document.querySelector('.top');

// // 修改样式
// comment_top.classList.add('mea');

// // 切换样式
// aqua.classList.toggle('mea');

// // 修改文字内容
// aqua.innerText = 'Mea';


// 功能模块: 发帖  
const tx = document.querySelector('#tx');
const total = document.querySelector('.total');

// 1. 文本域获得焦点时，让 total 显示
tx.addEventListener('focus', function() {
    total.style.opacity = 1;
})
// 2. 文本域失去焦点时，让 total 隐藏
tx.addEventListener('blur', function() {
    total.style.opacity = 0;
})
// 3. 检测用户输入
tx.addEventListener('input', function() {
    // console.log(tx.value.length);
    total.innerHTML = `${tx.value.length}/200字`;
})