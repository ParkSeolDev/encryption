
// const key = document.getElementById("key")
const keyBtn = document.getElementById("key-btn")
const dkeyBtn = document.getElementById("dkey-btn")
const datakeyBtn = document.getElementById("datakey-btn")
const request = new XMLHttpRequest()
const api = `http://localhost:8080/api/v1/rsa/generateKey`
var data = "Park Seol"
var key = ""
var num = ""
var dataEncrypted = ""
var privateKey = ""
var keyEncrypted = ""
var aesKey = ""

const handleClick = function(){
  window.alert("hello");
  getKey()
}

const decryptClick = function(){
  decryptKey()
}

const dataClick = function(){
  // decryptData()
}

keyBtn.onclick = handleClick;
dkeyBtn.onclick = decryptClick;
datakeyBtn.onclick = dataClick;
function guid() {
  function _s4() {
    return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
  }
  return _s4() + _s4() + '-' + _s4() + '-' + _s4() + '-' + _s4() + '-' + _s4() + _s4() + _s4();
}

function getKey(){
  request.open("GET", api);
  request.onload = () => {
    console.log(request.response)
    key = JSON.parse(request.response).publicKey
    privateKey = JSON.parse(request.response).privateKey
    console.log(key)
    // num : 대칭키
    num = guid()
    console.log("대칭키= " + num)

    
    const encrypt = new JSEncrypt();
    encrypt.setPublicKey(key);
    keyEncrypted = encrypt.encrypt(num);
    
    console.log("대칭키 암호화= " + keyEncrypted)

    dataEncrypted = CryptoJS.AES.encrypt(JSON.stringify(data), num).toString();
    console.log(dataEncrypted)
  };
  request.send()
}


const requestPost = new XMLHttpRequest()
const apiPost = `http://localhost:8080/api/v1/rsa/decrypt`


function decryptKey(){
  console.log('sssss')
  requestPost.open("POST", apiPost);
  requestPost.onload = () => {
    console.log(requestPost.response)
    // aesKey = JSON.parse(requestPost.response)
  };
  requestPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  requestPost.send('encryptText='+keyEncrypted+'&privateKey='+privateKey)
}

/*
function decryptKey() {
  axios.post(
    "http://localhost:8080/api/v1/rsa/decrypt",
    {
      encryptText: keyEncrypted,
      privateKey: privateKey,
    },
    {
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
    }
  ).then((response) => {
    console.log('success', response.data)
  }).catch((error) => {
    console.log('error', error)
  })
}
*/

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

document.querySelector("#encrypt").addEventListener("click", function() {
  const encrypt = new JSEncrypt();
  encrypt.setPublicKey(document.querySelector("#pubkey").value);
  const result = encrypt.encrypt(document.querySelector("#input").value);
  document.querySelector("#encrypted").value = result;
});

document.querySelector("#decrypt").addEventListener("click", function() {
  const decrypt = new JSEncrypt();
  decrypt.setPrivateKey(document.querySelector("#privkey").value);
  const text = decrypt.decrypt(document.querySelector("#encrypted").value);
  document.querySelector("#decrypted").value = text;
});
