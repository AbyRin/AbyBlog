const fallDiv = document.getElementById("fall_div");
const columnCount = window.innerWidth*1.5 <= window.screen.width ? 2 : 3;

// 爬虫图片路径
function loadImagesFromDirectory(directoryPath) {
    const imagePaths = [];
    const imageDirectory = directoryPath;

    for (let i = 1; i <= 20; i++) {
        const imagePath = imageDirectory + i + ".png";
        imagePaths.push(imagePath);
    }

    populateColumns(imagePaths);
}
// 调用
loadImagesFromDirectory("/image/reptile_image/");

function populateColumns(imagePaths) {
    // 创建瀑布列
    for (let i = 0; i < columnCount; i++) {
        const column = document.createElement("column");
        column.classList.add("column");
        fallDiv.appendChild(column);
    }

    // 渲染
    imagePaths.forEach((imagePath, index) => {
        const column = fallDiv.children[index % columnCount];
        const imageDiv = document.createElement("div");
        imageDiv.classList.add("image_div");
        imageDiv.style.boxShadow = "2px 2px 2px #bdbdbd";
        column.appendChild(imageDiv);

        const image = document.createElement("img");
        image.src = imagePath;
        image.alt = "Image " + (index + 1);
        // 图片加载失败时隐藏
        image.onerror = function() {
            imageDiv.style.display = 'none';
        };
        imageDiv.appendChild(image);

        const imageButtons = document.createElement("div");
        imageButtons.classList.add("image_buttons");
        imageDiv.appendChild(imageButtons);

        for (let j = 1; j <= 3; j++) {
            const button = document.createElement("div");
            button.classList.add("image_button");
            button.innerText = "+";
            imageButtons.appendChild(button);
        }
    });
}

// 【注意：已弃用，改用伪类】
// 功能: 鼠标移动到图片上时添加遮罩、按钮，移出图片外时移除遮罩、按钮
// 启用防抖
// function debounce(fn, t) {
//     let timer
//     return function () {
//         // 如果有定时器就清除
//         if (timer) clearTimeout(timer)
//         // 开启定时器
//         timer = setTimeout(function () {
//             fn()
//         }, t)
//     }
// }
// // 主函数
// function imgCover (image) {
//     image.addEventListener('mouseover', function() {
//         // 创建遮罩
//         const focusDiv = document.createElement("div");
//         focusDiv.classList.add("focus_div");
//         // 添加遮罩
//         if (!image.parentNode.contains(focusDiv)) {
//             image.parentNode.appendChild(focusDiv);
//             // 显示按钮
//             image.parentNode.querySelector('.image_buttons').style.display = "flex";     
//             image.addEventListener('mouseout', function() {
//                 // 移除遮罩
//                 if (image.parentNode.contains(focusDiv)) {
//                     image.parentNode.removeChild(focusDiv);
//                 }
//                 // 隐藏按钮
//                 image.parentNode.querySelector('.image_buttons').style.display = "none";
//             })
//         }
//     })
// }
// const images = document.querySelectorAll(".image_div img");
// for (let i = 0; i < images.length; i++) {
//     const image = images[i]
//     image.addEventListener('mouseover', debounce(function() {imgCover(image)}, 200))
// }

function throttle(fn, t) {  // 功能: 页面resize时刷新（启用节流）
    let timer = null;
    return function () {
        if (!timer) {
            timer = setTimeout(function () {
                fn(),
                // 清空定时器
                timer = null;
            }, t);
        }
    }
}
// 主函数
// 注意: 为了保证刷新的流畅, 浏览器要启用缓存
function refreshOnResize() {
    location.reload();
}
window.addEventListener('resize', throttle(refreshOnResize, 500))
