export const switchMenuItem = (items: Array<string>, path: string) => {
    let selected = "";
    items.forEach((item: string) => {
        if (path.length >= item.length && path.substr(0, item.length) == item) {
            selected = item;
        }
    });
    return selected;
}