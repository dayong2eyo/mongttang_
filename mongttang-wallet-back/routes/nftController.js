import express from "express";
import { withdraw } from "../api/blockchain.js";
import { create } from "ipfs-http-client";
import multer from "multer";

// Node 에서는 require 이 뒤에 .js 를 붙이지 않아도 된다.

const router = express.Router();

const ipfs = create({
  host: "j8a308.p.ssafy.io",
  port: 5001,
  protocol: "http",
});
const upload = multer();

router.post("/ipfs", upload.array("images", 20), async (request, response) => {
  try {
    // Extract the image data from the request
    const imageData = request.files.map((file) => file.buffer);

    // Add the received image data to IPFS and get their CIDs
    console.log("업로드 시도중");
    const cidPromises = imageData.map((data) => ipfs.add(data));
    const cids = await Promise.all(cidPromises);
    const cidStrings = cids.map((cid) => cid.cid.toString());
    console.log(`Images added to IPFS with CIDs ${cidStrings}`);

    // Return the CIDs as a response to the client
    response.statusCode = 200;
    response.setHeader("Content-Type", "text/plain");
    response.end(cidStrings.join(","));
  } catch (error) {
    console.error("Error adding images to IPFS:", error);
    response.statusCode = 500;
    response.end("Error adding images to IPFS");
  }
});

router.post("/withdraw", (request, response) => {
  const body = request.body;
  console.log(request);
  withdraw(body.privateKey, body.tokenId, body.amount);
});

export default router;