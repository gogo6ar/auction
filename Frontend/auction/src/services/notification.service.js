import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import UserService from "@/services/user.service"
import properties from "@/properties";

const STOMP_URL = properties.API_URL + '/websocket';

class NotificationService {
    connect() {
        this.socket = new SockJS(STOMP_URL);
        this.error = "";
        this.stompClient = Stomp.over(this.socket);
        if(UserService.getUser()) {
            this.stompClient.connect(
                {"username": UserService.getUser().userDto.id},
                () => {
                    return this.stompClient.subscribe("/notification/" + UserService.getUser().userDto.id + "/secured/user");
                },
                error => {
                    this.$notify({
                        text: error.response.data.errorMessage,
                        type: 'error'
                    });
                    this.error = error;
                }
            );
        }
        return this.error;
    }
}

export default new NotificationService();
