DATA_DIR=/tmp/minio/data
LOCAL_PORT=9090
CONTAINER_NAME=minio_bucket
USERNAME=admin
PASSWORD=admin1234

mkdir -p $DATA_DIR

docker run \
  -p $LOCAL_PORT:9000 \
  --name $CONTAINER_NAME \
  -e "MINIO_ROOT_USER=${USERNAME}" \
  -e "MINIO_ROOT_PASSWORD=${PASSWORD}" \
  -v $DATA_DIR:/data \
  quay.io/minio/minio server /data --console-address ":$LOCAL_PORT"
