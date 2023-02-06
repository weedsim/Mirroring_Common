FROM node:14

WORKDIR /root/S08P12A706/front-end

COPY package.json ./
COPY package-lock.json ./

RUN npm install

COPY ./ ./

CMD ["npm", "run", "serve"]
