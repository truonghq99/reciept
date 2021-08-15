var lap = document.getElementsByClassName("for");

for (let i = 0; i < lap.length; i++) {
    const elements = document.getElementsByName(i);
    const idItem = elements[0].getAttribute('id');
    const quantity = 'quantity' + idItem;
    const price = 'price' + idItem;
    const totalPrice = 'totalPrice' + idItem;

    $("#" + quantity).change(function() {
        const newQuantity = $(this).val();
        const newPrice = $("#" + price).val();
        var newTotalPrice = parseFloat(newQuantity) * parseFloat((newPrice));
        $("#" + totalPrice).val(newTotalPrice);
        var sum = 0;
        $(".totalPrice").each(function() {
            sum += parseFloat($(this).val());
        });
        console.log(sum);
        $("#totalCost").val(sum);
    });
}