const axios = require("axios").default;

axios.get("https://api.github.com/repos/xavifortes/serta-client/releases/latest").then(res => {
    console.log("::set-output name=number::" + (parseInt(res.data.tag_name) + 1));
});