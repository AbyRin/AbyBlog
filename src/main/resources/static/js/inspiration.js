const fallDiv = document.getElementById("fall_div");
        const columnCount = window.innerWidth >= window.screen.width * 1.5 ? 2 : 3; // 150% or more screen width for 2 columns

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
                const column = document.createElement("div");
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