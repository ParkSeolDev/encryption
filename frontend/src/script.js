// const key = document.getElementById("key")
const keyBtn = document.getElementById("key-btn");
const dkeyBtn = document.getElementById("dkey-btn");
const datakeyBtn = document.getElementById("datakey-btn");
const request = new XMLHttpRequest();
const api = `http://localhost:8080/api/v1/rsa/generateKey`;
var data1 = "선행연구팀 박설";
var key = "";
var num = "";
var dataEncrypted = "";
var RSAprivateKey = "";
var keyEncrypted = "";
var aesKey = "";
var formdata = new FormData();
var dataResult = "";

const handleClick = function () {
  window.alert("hello");
  getKey();
};

const decryptClick = function () {
  decryptKey();
  console.log(aesKey);
};

const dataClick = function () {
  decryptData();
  console.log(dataResult.replace("[\\u0001-\\u0008\\u000B-\\u001F]", ""));
};

keyBtn.onclick = handleClick;
dkeyBtn.onclick = decryptClick;
datakeyBtn.onclick = dataClick;
function guid() {
  function _s4() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
  }
  // return _s4() + _s4() + "1" + _s4() + "1" + _s4() + "1" + _s4() + "1" + _s4() + _s4();
  return _s4() + _s4() + _s4() + _s4();
}

function getKey() {
  request.open("GET", api);
  request.onload = () => {
    console.log(request.response);
    key = JSON.parse(request.response).publicKey;
    RSAprivateKey = JSON.parse(request.response).privateKey;
    console.log(key);
    // num : 대칭키
    num = guid();
    console.log("대칭키= " + num);

    const encrypt = new JSEncrypt();
    encrypt.setPublicKey(key);
    keyEncrypted = encrypt.encrypt(num);

    console.log("대칭키 암호화= " + keyEncrypted);

    // dataEncrypted = CryptoJS.AES.encrypt(JSON.stringify(data1), num).toString();
    dataEncrypted = this.encodeByAES(num, data1);
    console.log(dataEncrypted);
  };
  request.send();
}

function encodeByAES(key, data) {
  const cipher = CryptoJS.AES.encrypt(data, CryptoJS.enc.Utf8.parse(key), {
    iv: CryptoJS.enc.Utf8.parse(key),
    pad: CryptoJS.pad.NoPadding,
    mode: CryptoJS.mode.CBC,
  });
  return cipher.toString();
}

const requestPost = new XMLHttpRequest();
const apiPost = `http://localhost:8080/api/v1/rsa/decrypt`;

formdata.append("encryptText", keyEncrypted);
formdata.append("privateKey", RSAprivateKey);
/*
function decryptKey() {
  console.log("sssss");
  requestPost.open("POST", apiPost, true);
  requestPost.onload = () => {
    console.log(requestPost.response);
    // aesKey = JSON.parse(requestPost.response)
  };
  requestPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  // requestPost.send("encryptText=" + keyEncrypted + "&privateKey=" + privateKey);
  requestPost.send(formdata);
}
*/
function decryptKey() {
  fetch("http://localhost:8080/api/v1/rsa/decrypt", {
    method: "POST",
    cache: "no-cache",
    headers: {
      Accept: "application/json",
    },
    // body: formdata, // body 부분에 폼데이터 변수를 할당
    body: new URLSearchParams({
      // 일반 객체를 fordata형식으로 변환해주는 클래스
      encryptText: keyEncrypted,
      privateKey: RSAprivateKey,
    }),
  })
    // .then((response) => (aesKey = response.text()));

    .then((response) => (aesKey = response.text()))
    .then((data) => (aesKey = data.toString()));

  // }).then((response) => (aesKey = JSON.parse(response).toString()));
  // }).then((response) => (aesKey = JSON.stringify(response)));
}

function decryptData() {
  fetch("http://localhost:8080/api/v1/aes/decrypt", {
    method: "POST",
    cache: "no-cache",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
    },
    // body: formdata, // body 부분에 폼데이터 변수를 할당
    body: new URLSearchParams({
      // 일반 객체를 fordata형식으로 변환해주는 클래스
      key: aesKey,
      encryptedData: dataEncrypted,
    }),
  }).then(
    (response) => (dataResult = response.text().then((data) => (dataResult = data.toString())))
  );
  // }).then((response) => (aesKey = JSON.parse(response).toString()));
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

document.querySelector("#encrypt").addEventListener("click", function () {
  const encrypt = new JSEncrypt();
  encrypt.setPublicKey(document.querySelector("#pubkey").value);
  const result = encrypt.encrypt(document.querySelector("#input").value);
  document.querySelector("#encrypted").value = result;
});

document.querySelector("#decrypt").addEventListener("click", function () {
  const decrypt = new JSEncrypt();
  decrypt.setPrivateKey(document.querySelector("#privkey").value);
  const text = decrypt.decrypt(document.querySelector("#encrypted").value);
  document.querySelector("#decrypted").value = text;
});
