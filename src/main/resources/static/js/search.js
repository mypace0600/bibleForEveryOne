window.onload = function (){

    let bookNameList = document.getElementsByClassName('bookName');

    for(let i = 0 ; i < bookNameList.length ; i++){
        bookNameList[i].onclick = function (){
            let bookNum = this.id;
            console.log(bookNum);
            location.href="/select/book/"+bookNum;
        };
    }

    let chapterBoxList = document.getElementsByClassName('chapterBox');

    for(let i = 0 ; i < chapterBoxList.length ; i++){
        chapterBoxList[i].onclick = function (){
            let chapterBoxNum = this.id;
            console.log(chapterBoxNum);
        }
    }
};