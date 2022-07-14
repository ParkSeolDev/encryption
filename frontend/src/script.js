
// const key = document.getElementById("key")
const keyBtn = document.getElementById("key-btn")

const request = new XMLHttpRequest()
const api = `http://localhost:8080/api/v1/rsa/getPublicKey`
var data = "Park Seol"
var key = ""
var num = ""
var encrypted =""

const handleClick = function(){
  window.alert("hello");
  getKey()
  decryptKey()
}

keyBtn.onclick = handleClick;

function guid() {
  function _s4() {
    return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
  }
  return _s4() + _s4() + '-' + _s4() + '-' + _s4() + '-' + _s4() + '-' + _s4() + _s4() + _s4();
}

function getKey(){
  request.open("GET", api);
  request.onload = () => {
    // console.log(request.response);
    key = request.response
    // num : 대칭키
    num = guid()
    console.log("대칭키= " + num)
    // RSASetPublic(key.publicKey, num)
    encrypted = RSAEncrypt(num).toString();
    console.log(encrypted)

    dataEncrypted = CryptoJS.AES.encrypt(JSON.stringify(data), num).toString();
    console.log(dataEncrypted)
  };
  request.send();
}


const requestPost = new XMLHttpRequest()
const apiPost = `http://localhost:8080/api/v1/rsa/decrypt`

function decryptKey(){
  requestPost.open("POST", apiPost);
  // request.send("encryptText=encrypted");
}


// $.ajax({
//   url:"http://localhost:8080/api/v1/rsa/decryptByPrivateKey",
//   type:'POST',
//   data: encrypted,
//   success:function(data){
//       alert("완료!");
//       window.opener.location.reload();
//       self.close();
//   },
//   error:function(jqXHR, textStatus, errorThrown){
//       alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
//       self.close();
//   }
// });