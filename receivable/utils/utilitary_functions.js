export function getShippingDate() {
    const currentDate = new Date();    
    const requiredDateMili = currentDate.getTime() + 1000 * 60 * 60 * 24 * 3;
    return (new Date(requiredDateMili)).toString();
}