FROM node:14
WORKDIR /app
COPY . .
RUN npm install
COPY . .
RUN npm run build
ENTRYPOINT ["npm", "run", "serve"]
