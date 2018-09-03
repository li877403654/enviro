/**
 * Created by zzl on 2015/7/5.
 */
$(function () {
    //var $div = $('div.op');
    //var dx = $('table.form').find('td:first').offset().left - $div.offset().left;
    //$div.css({
    //    'padding-left': dx + 'px',
    //    'text-align': 'left'
    //});

    $.validator.setDefaults({
            errorPlacement: function (error, $el) {
                error.appendTo($el.closest('td'));
            }
        }
    );

    var submitUrl = window.submitUrl || 'save.json';
    var submitOptions = {
        dataType: 'json',
        url: submitUrl,
        data: {action: 'save'},
        traditional: true,
        success: function (result) {
            if (result.ok) {
                if(window.submittedCallback) {
                    window.submittedCallback(result);
                }
                else {
                    alert('保存成功');
                    returnUrl('query.html');
                }
            }
            else {
                var msg = result.msg || result.message;
                var errors = result.errors;
                if(!msg && errors) {
                    msg = '';
                    for(var key in errors) {
                        if(!errors.hasOwnProperty(key)) {
                            continue;
                        }
                        if(msg) {
                            msg += '\n';
                        }
                        msg += key + ': ' + errors[key];
                    }
                }
                alert(msg);
            }
        },
        error: function () {
            alert('form submit error..')
        }
    };

    var $form = $('form');
    if(!$form.length) {
        return;
    }
    var validateOptions = $.extend({}, window.validateOptions, {
        submitHandler: function() {
            if(window.onFormSubmit) {
                if(window.onFormSubmit($form[0], submitOptions) === false) {
                    return;
                }
            }
            $form.ajaxSubmit(submitOptions);
        }
    });
    if(window.newController) {
        submitOptions.url = 'save.json';
    }
    $form.validate(validateOptions);

});


//
$(function() {
    var $form = $('form');
    if($form.attr('autocomplete') == 'off') {
        if(!$form.children('input[name=somefakename]').length) {
            $form.prepend('<input style="display:none;" type="text" name="somefakename"/>');
        }
    }
});