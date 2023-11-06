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

        // 创建：image_div
        const imageDiv = document.createElement("div");
        imageDiv.classList.add("image_div");
        imageDiv.style.boxShadow = "2px 2px 2px #bdbdbd";
        column.appendChild(imageDiv);

        // 创建：图片
        const image = document.createElement("img");
        image.src = imagePath;
        image.alt = "Image " + (index + 1);
        // 图片加载失败时隐藏
        image.onerror = function() {
            imageDiv.style.display = 'none';
        };
        imageDiv.appendChild(image);

        // 创建：按钮组 image_buttons
        const imageButtons = document.createElement("div");
        imageButtons.classList.add("image_buttons");
        imageDiv.appendChild(imageButtons);

        // 创建：按钮 image_button
        for (let j = 1; j <= 3; j++) {
            const button = document.createElement("div");
            button.classList.add("image_button");

            button.id = "btn" + j;
            button.style.display = "flex"
            button.style.justifyContent = "center";
            button.style.alignItems = "center";
            
            // 创建：按钮图标 img
            let imgSrc = "";
            if (j === 1) {
                imgSrc = "image/icon/icons8-heart-50-gray.png";
            } else if (j === 2) {
                imgSrc = "image/icon/icons8-bookmark-60-gray.png";
            } else {
                imgSrc = "image/icon/icons8-download-48-gray.png";
            }
            
            const img = document.createElement("img");
            img.src = imgSrc;
            img.style.maxWidth = "24px";
            img.alt = "";
            
            button.appendChild(img);
            imageButtons.appendChild(button);
        }
    });  // 完成渲染

    // 遍历每个 image_buttons 并为其中的按钮绑定事件处理程序
    function handleButtonHover(event) {
        const button = event.currentTarget;
        // console.log(button)
        const img = button.querySelector('img');
        // console.log(img)
        const btnId = button.id;
        // console.log(btnId)
    
        if (event.type === 'mouseover') {
            // console.log('鼠标进来')
            if (btnId === 'btn1') {
                img.src = "image/icon/icons8-heart-50-color.png";
                button.style.borderRadius = "50px";
            } else if (btnId === 'btn2') {
                img.src = "image/icon/icons8-bookmark-60-color.png";
                button.style.borderRadius = "50px";
            } else {
                img.src = "image/icon/icons8-download-48-color.png";
                button.style.borderRadius = "50px";
            }
        } else if (event.type === 'mouseout') {
            // console.log('鼠标出去')
            if (btnId === 'btn1') {
                img.src = "image/icon/icons8-heart-50-gray.png";
                button.style.borderRadius = "10px";
            } else if (btnId === 'btn2') {
                img.src = "image/icon/icons8-bookmark-60-gray.png";
                button.style.borderRadius = "10px";
            } else {
                img.src = "image/icon/icons8-download-48-gray.png";
                button.style.borderRadius = "10px";
            }
        }
    }

    // 获取所有 image_button 并 绑定事件
    const allImageButtons = document.querySelectorAll(".image_button");

    allImageButtons.forEach(button => {
        button.addEventListener('mouseover', handleButtonHover);
        button.addEventListener('mouseout', handleButtonHover);
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

// 电梯：backTop键
document.addEventListener('DOMContentLoaded', function() {
    (function () {
        const backTop = document.querySelector('#backTop')
        const img = backTop.querySelector('img');
        // 修改样式
        backTop.addEventListener('mouseover', function () {
           img.src = '/image/icon/icons8-top-100-white.png'
        })
        backTop.addEventListener('mouseout', function () {
            img.src = '/image/icon/icons8-top-100-gray.png'
         })
        // backTop功能
        backTop.addEventListener('click', function () {
            window.scrollTo(0, 0)
        })
    })();
});