$(document).ready(function(){
    $('#deleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var objectName = button.data('menu-item') // Extract info from data-object-name attribute
        var objectId= button.data('menu-id')
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('#deleteModalHeader').text(objectName)
        modal.find('#deleteBtn').attr('href','/restaurant/delete/' + objectId);
    })

    $('#deleteCampaignModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var objectName = button.data('campaign-name') // Extract info from data-object-name attribute
        var objectId= button.data('campaign-id')
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('#deleteModalHeader').text(objectName)
        modal.find('#deleteBtn').attr('href','/restaurant/deleteCampaign/' + objectId);
    })
})