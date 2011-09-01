function updateLocations(element, parentId, selectedId)
{
    $.parseJSON("/webservice/locations/" + parentId + "/children/", function(data)
    {
        createLocations(element, data, selectedId);
    });
    removeList(element);
}

function createLocations(element, data, selectedId)
{
    for (var i = 0; i < data.length; i++)
    {
        var option = new Option(data[i].name, data[i].id)
        if (selectedId != undefined && selectedId == data[i].id)
        {
            option.selected = true;
        }
        $('[id=' + element + ']').options.add(option);
    }
}

function removeLocations(element)
{
    var len = $('[id=' + element + ']')[0].length;
    for (var i = 1; i < len; i++)
    {
        $('[id=' + element + ']')[0].remove(1);
    }
}

function updateLocations(element)
{
    $.parseJSON("/webservice/locations/all", function(datas)
    {
        $.each(datas, function(i, items)
        {
            $.each(items, function(i, item)
            {
                var option = new Option(item.name, item.id);
                $('[id=' + element + ']')[0].add(option);
            });
        });
    });
}
