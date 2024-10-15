FROM node:18.16 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
# CMD ["npm","run","serve"]
RUN npm run build

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
# # production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]