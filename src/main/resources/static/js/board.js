let index = {
    init: function(){
        $("#btn-write").on("click", ()=>{ //function(){}, ()=> this를 바인딩하기 위해서!
            this.save();
        });
    },

 
 save: function(){
    //  alert('user의 save함수 호출됨');
 let data = {
     title:$("#title").val(),
     content:$("#content").val(),
 };
 
 $.ajax({
 type: "POST",
 url: "/api/board",
 data: JSON.stringify(data), // Json으로 변경, http body데이터 MIME타입 필요
 contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
 dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
 }).done(function(resp){
  console.log(resp);
 alert("글쓰기가 완료되었습니다.");
 location.href = "/";
 }).fail(function(error){
 alert(JSON.stringify(error));
 }); 
  },
 
}

index.init();
