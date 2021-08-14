var lap = document.getElementsByClassName("for");
var sum = 0;

for (let i = 0; i < lap.length; i++) {
    const elements = document.getElementsByName(i);
    const idItem = elements[0].getAttribute('id');
    const quantity = 'quantity' + idItem;
    const price = "price" + idItem;
    const amount = "amount" + idItem;
    const discount = "discount" + idItem;
    const totalPrice = "totalPrice" + idItem;
    console.log(quantity);
    $("#" + quantity).change(function() {
        const newQuantity = $("#" + quantity).val();
        const newPrice = $("#" + price).val();
        var newAmount = parseFloat(newQuantity) * parseFloat(newPrice);
        $("#" + amount).val(newAmount);
        const newDiscount = $("#" + discount).val();
        const newTotalPrice = parseFloat(newAmount) - parseFloat(newAmount * newDiscount / 100);
        $("#" + totalPrice).val(newTotalPrice);
        var sum = 0;
        $(".totalPrice").each(function() {
            sum += parseFloat($(this).val());
        });
        $("#billPrice").val(sum);
        const discountBill = $("#billDiscount").val();
        var totalBill = sum - sum * parseFloat(discountBill) / 100;
        $("#billTotal").val(totalBill);


    });

    $("#" + discount).change(function() {
        var newAmount = $("#" + amount).val();
        const newDiscount = parseFloat($("#" + discount).val());
        console.log(typeof newDiscount)
        if (newDiscount < 0 || newDiscount > 100) {
            alert("Wrong discount number");
        }
        var newTotal = parseFloat(newAmount) - parseFloat(newDiscount) * parseFloat(newAmount) / 100;
        $("#" + totalPrice).val(newTotal);
        var sum = 0;
        $(".totalPrice").each(function() {
            sum += parseFloat($(this).val());
        });
        $("#billPrice").val(sum);
        const billDiscount = $("#billDiscount").val();
        var totalBill = sum - sum * parseFloat(billDiscount) / 100;
        $("#billTotal").val(totalBill);

    });

}

$("#billDiscount").change(function() {
    const billPrice = $("#billPrice").val();
    const billDiscount = $("#billDiscount").val();
    var billTotal = parseFloat(billPrice) - parseFloat(billDiscount) * parseFloat(billPrice) / 100;
    $("#billTotal").val(billTotal);
});