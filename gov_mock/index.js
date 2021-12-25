'use strict';

const express = require('express');

// Constants
const PORT = 80;
const HOST = '0.0.0.0';

// App
const app = express();
app.post('/register', (req, res) => {
  console.log(req.query);
  res.send('Received.');
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
