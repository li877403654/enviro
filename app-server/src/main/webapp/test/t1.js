(function (gWinDx, gWinDy, withColor) {

    function collectTextInfos(winDx, winDy, withColor) {
        function parsePixel(value, contextElem) {
            if (!value) {
                return 0;
            }
            if (value == 'thick') {
                return 5;
            }
            if (value == 'medium') {
                return 3;
            }
            if (value == 'thin') {
                return 1;
            }
            var len = value.length;
            if (len > 2) {
                var unit = value.substr(len - 2, 2);
                if (unit == 'px') {
                    return parseInt(value.substr(0, len - 2));
                }
            }
            //else
            var div = document.createElement('div');
            div.style.cssText = "position:absolute;width:" + value + ";height: 24px; background-color:red;";
            var parentEl = contextElem.parentElement;
            parentEl.appendChild(div);
            var w = div.clientWidth;
            parentEl.removeChild(div);
            return w;
        }

        var ta = document.getElementById('ta_textInfos');
        if (ta) {
            document.body.removeChild(ta);
        }
        var winHeight = document.documentElement.clientHeight;
        if (!winHeight) {
            winHeight = document.body.clientHeight;
        }

        function clientOffset(elem) {
            var offset = {
                left: elem.clientX,
                top: elem.clientY
            };
            if (elem.clientY !== undefined) {
                return offset;
            }
            var box = {top: 0, left: 0};
            if (typeof elem.getBoundingClientRect !== 'undefined') {
                try {
                    box = elem.getBoundingClientRect();
                }
                catch (e) {
                    //xx
                }
            }
            return {
                top: box.top,
                left: box.left
            };
        }

        function isDescendant(parent, child) {
            var node = child.parentNode;
            while (node != null && node !== document.body) {
                if (node == parent) {
                    return true;
                }
                node = node.parentNode;
            }
            return false;
        }

        function isVisible(elem, offset) {
            if (elem === document.body) {
                return true;
            }
            var style = elem.currentStyle;
            var visibility = style.visibility;
            if (style.display == 'none' ||
                visibility == 'hidden' ||
                visibility == 'collapse') {
                return false;
            }
            else {
                return true;
            }
        }

        function isClickable(elem, offset) {
            var elemAtPoint = document.elementFromPoint(offset.left + 4, offset.top + 4);
            if (!elemAtPoint) {
                return false;
            }
            if (elemAtPoint === elem || isDescendant(elem, elemAtPoint)) {
                return true;
            }
            else {
                return false;
            }
        }

        function getTextInfos(elem) {
            var textInfos = [];
            var children = elem.childNodes;
            var n;
            var text = '';
            var offset = clientOffset(elem);
            if (!isVisible(elem, offset)) {
                return [];
            }
            for (n = 0; n < children.length; n++) {
                var child = children[n];
                if (child.nodeType == 3) {
                    var tText = child.nodeValue.replace(/^\s+|\s+$/g, '');
                    tText = tText.replace(/\n/g, ' ');
                    text = text + tText;
                    continue;
                }
                if (child.nodeType != 1) {
                    continue;
                }
                if (child.nodeName == 'SCRIPT') {
                    continue;
                }
                var childTextInfos = getTextInfos(child);
                textInfos = textInfos.concat(childTextInfos);
            }
            if (!children.length) {
                if (elem.nodeName == 'INPUT') {
                    var type = elem.type;
                    if (type == 'text' || type == 'button' || type == 'submit') {
                        text = elem.value;
                    }
                }
            }
            if (text) {
                if (withColor) {
                    text += '::{"color":"' + elem.currentStyle.color + '"}';
                }
                if (offset.top < 0 || offset.top >= winHeight) {
                    //xx
                }
                else if (!isClickable(elem, offset)) {
                    //xx
                }
                else textInfos.push({
                        offset: {
                            left: Math.round(offset.left + winDx),
                            top: Math.round(offset.top + winDy)
                        },
                        text: text
                    });
            }
            return textInfos;
        }

        var textInfos = getTextInfos(document.body);
        var frames = document.getElementsByTagName('iframe');
        for (var nFrame = 0; nFrame < frames.length; nFrame++) {
            var frame = frames[nFrame];
            if (!isVisible(frame)) {
                continue;
            }
            var style = frame.currentStyle;
            if (!style) {
                style = getComputedStyle(frame, false);
            }
            var bLeft = parsePixel(style.borderLeftWidth, frame);
            var bTop = parsePixel(style.borderTopWidth, frame);
            var frameOffset = clientOffset(frame);
            var tWinDx = Math.round(winDx + bLeft + frameOffset.left);
            var tWinDy = Math.round(winDy + bTop + frameOffset.top);

            frame.setAttribute('winDxDy', '' + tWinDx + ',' + tWinDy);
        }
        return textInfos;
    }

    var textInfos = collectTextInfos(gWinDx, gWinDy, withColor);
    var texts = '';
    var n;
    for (n = 0; n < textInfos.length; n++) {
        var textInfo = textInfos[n];
        var offset = textInfo.offset;
        texts = texts + '``_' + offset.left + ',' + offset.top + ':' + textInfo.text + '\n';
    }
    ta = document.createElement('input');
    ta.id = 'ta_textInfos';
    ta.type = 'hidden';
    ta.value = texts;
    document.body.appendChild(ta);
});