
var CryptoJS = require("crypto-js");

var secretKey = 'secret key';
var data = {
    username: 'Park-Seol',
    age: 31
};
console.log('original:', data);

// encrypt
var encrypted = CryptoJS.AES.encrypt(JSON.stringify(data), secretKey).toString();
console.log('encrypt:', encrypted);

// decrypt
var bytes = CryptoJS.AES.decrypt(encrypted, secretKey);
var decrypted = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
console.log('decrypted:', data);
