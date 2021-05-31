import {history} from "../store/store";

export const processException = (e: any) => {
    if (e.name == "BadResponse" && e.response.status == 401) {
        console.log(e);
        history.push("/logout");
        // reLogin();
    }
}