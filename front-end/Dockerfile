FROM node:14.15.1-alpine as build
WORKDIR /home/ubuntu/S08P12A706
COPY front-end/package*.json ./
RUN npm install
COPY front-end .
RUN npm run build

COPY --from=build /app/dist /var/www/html