$(function() {
    $('#log_grid_1.paginated').each(function() { 
        var currentPage = 0;
        var numPerPage = 8;
        var $grid = $(this);
        $grid.bind('repaginate', function() {
            $grid.find('.logcol').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
        });
        $grid.trigger('repaginate');
        var numRows = $grid.find('.logcol').length;
        var numPages = Math.ceil(numRows / numPerPage);
        var $pager = $('<div class="pager">Page </div>');
        for (var page = 0; page < numPages; page++) {
            $('<span class="page-number"></span>').text(page + 1).bind('click', {
                newPage: page
            }, function(event) {
                currentPage = event.data['newPage'];
                $grid.trigger('repaginate');
                $(this).addClass('active').siblings().removeClass('active');
            }).appendTo($pager).addClass('clickable');
        }
        $pager.insertBefore($grid).find('span.page-number:first').addClass('active');
    });
});

// jquery.popup.js code start



;
(function ($) {

    var $window = $(window);
    var options = {};
    var zindexvalues = [];
    var lastclicked = [];
    var scrollbarwidth;
    var bodymarginright = null;
    var opensuffix = '_open';
    var closesuffix = '_close';
    var stack = [];
    var transitionsupport = null;
    var opentimer;
    var iOS = /(iPad|iPhone|iPod)/g.test(navigator.userAgent);

    var methods = {

        _init: function (el) {
            var $el = $(el);
            var options = $el.data('popupoptions');
            lastclicked[el.id] = false;
            zindexvalues[el.id] = 0;

            if (!$el.data('popup-initialized')) {
                $el.attr('data-popup-initialized', 'true');
                methods._initonce(el);
            }

            if (options.autoopen) {
                setTimeout(function() {
                    methods.show(el, 0);
                }, 0);
            }
        },

        _initonce: function (el) {
            var $el = $(el);
            var $body = $('body');
            var $wrapper;
            var options = $el.data('popupoptions');
            var css;

            bodymarginright = parseInt($body.css('margin-right'), 10);
            transitionsupport = document.body.style.webkitTransition !== undefined ||
            document.body.style.MozTransition !== undefined ||
            document.body.style.msTransition !== undefined ||
            document.body.style.OTransition !== undefined ||
            document.body.style.transition !== undefined;

            if (options.type == 'tooltip') {
                options.background = false;
                options.scrolllock = false;
            }

            if (options.backgroundactive) {
                options.background = false;
                options.blur = false;
                options.scrolllock = false;
            }

            if (options.scrolllock) {
                // Calculate the browser's scrollbar width dynamically
                var parent;
                var child;
                if (typeof scrollbarwidth === 'undefined') {
                    parent = $('<div style="width:50px;height:50px;overflow:auto"><div/></div>').appendTo('body');
                    child = parent.children();
                    scrollbarwidth = child.innerWidth() - child.height(99).innerWidth();
                    parent.remove();
                }
            }

            if (!$el.attr('id')) {
                $el.attr('id', 'j-popup-' + parseInt((Math.random() * 100000000), 10));
            }

            $el.addClass('popup_content');

            $body.prepend(el);

            $el.wrap('<div id="' + el.id + '_wrapper" class="popup_wrapper" />');

            $wrapper = $('#' + el.id + '_wrapper');

            $wrapper.css({
                opacity: 0,
                visibility: 'hidden',
                position: 'absolute'
            });

            // Make div clickable in iOS
            if (iOS) {
                $wrapper.css('cursor', 'pointer');
            }

            if (options.type == 'overlay') {
                $wrapper.css('overflow','auto');
            }

            $el.css({
                opacity: 0,
                visibility: 'hidden',
                display: 'inline-block'
            });

            if (options.setzindex && !options.autozindex) {
                $wrapper.css('z-index', '100001');
            }

            if (!options.outline) {
                $el.css('outline', 'none');
            }

            if (options.transition) {
                $el.css('transition', options.transition);
                $wrapper.css('transition', options.transition);
            }

            // Hide popup content from screen readers initially
            $el.attr('aria-hidden', true);

            if ((options.background) && (!$('#' + el.id + '_background').length)) {

                $body.prepend('<div id="' + el.id + '_background" class="popup_background"></div>');

                var $background = $('#' + el.id + '_background');

                $background.css({
                    opacity: 0,
                    visibility: 'hidden',
                    backgroundColor: options.color,
                    position: 'fixed',
                    top: 0,
                    right: 0,
                    bottom: 0,
                    left: 0
                });

                if (options.setzindex && !options.autozindex) {
                    $background.css('z-index', '100000');
                }

                if (options.transition) {
                    $background.css('transition', options.transition);
                }
            }

            if (options.type == 'overlay') {
                $el.css({
                    textAlign: 'left',
                    position: 'relative',
                    verticalAlign: 'middle'
                });

                css = {
                    position: 'fixed',
                    width: '100%',
                    height: '100%',
                    top: 0,
                    left: 0,
                    textAlign: 'center'
                };

                if(options.backgroundactive){
                    css.position = 'relative';
                    css.height = '0';
                    css.overflow = 'visible';
                }

                $wrapper.css(css);

                // CSS vertical align helper
                $wrapper.append('<div class="popup_align" />');

                $('.popup_align').css({
                    display: 'inline-block',
                    verticalAlign: 'middle',
                    height: '100%'
                });
            }

            // Add WAI ARIA role to announce dialog to screen readers
            $el.attr('role', 'dialog');

            var openelement =  (options.openelement) ? options.openelement : ('.' + el.id + opensuffix);

            $(openelement).each(function (i, item) {
                $(item).attr('data-popup-ordinal', i);

                if (!item.id) {
                    $(item).attr('id', 'open_' + parseInt((Math.random() * 100000000), 10));
                }
            });

            // Set aria-labelledby (if aria-label or aria-labelledby is not set in html)
            if (!($el.attr('aria-labelledby') || $el.attr('aria-label'))) {
                $el.attr('aria-labelledby', $(openelement).attr('id'));
            }

            // Show and hide tooltips on hover
            if(options.action == 'hover'){
                options.keepfocus = false;

                // Handler: mouseenter, focusin
                $(openelement).on('mouseenter', function (event) {
                    methods.show(el, $(this).data('popup-ordinal'));
                });

                // Handler: mouseleave, focusout
                $(openelement).on('mouseleave', function (event) {
                    methods.hide(el);
                });

            } else {

                // Handler: Show popup when clicked on `open` element
                $(document).on('click', openelement, function (event) {
                    event.preventDefault();

                    var ord = $(this).data('popup-ordinal');
                    setTimeout(function() { // setTimeout is to allow `close` method to finish (for issues with multiple tooltips)
                        methods.show(el, ord);
                    }, 0);
                });
            }

            if (options.closebutton) {
                methods.addclosebutton(el);
            }

            if (options.detach) {
                $el.hide().detach();
            } else {
                $wrapper.hide();
            }
        },

        /**
         * Show method
         *
         * @param {object} el - popup instance DOM node
         * @param {number} ordinal - order number of an `open` element
         */
        show: function (el, ordinal) {
            var $el = $(el);

            if ($el.data('popup-visible')) return;

            // Initialize if not initialized. Required for: $('#popup').popup('show')
            if (!$el.data('popup-initialized')) {
                methods._init(el);
            }
            $el.attr('data-popup-initialized', 'true');

            var $body = $('body');
            var options = $el.data('popupoptions');
            var $wrapper = $('#' + el.id + '_wrapper');
            var $background = $('#' + el.id + '_background');

            // `beforeopen` callback event
            callback(el, ordinal, options.beforeopen);

            // Remember last clicked place
            lastclicked[el.id] = ordinal;

            // Add popup id to popup stack
            setTimeout(function() {
                stack.push(el.id);
            }, 0);

            // Calculating maximum z-index
            if (options.autozindex) {

                var elements = document.getElementsByTagName('*');
                var len = elements.length;
                var maxzindex = 0;

                for(var i=0; i<len; i++){

                    var elementzindex = $(elements[i]).css('z-index');

                    if(elementzindex !== 'auto'){

                        elementzindex = parseInt(elementzindex, 10);

                        if(maxzindex < elementzindex){
                            maxzindex = elementzindex;
                        }
                    }
                }

                zindexvalues[el.id] = maxzindex;

                // Add z-index to the background
                if (options.background) {
                    if (zindexvalues[el.id] > 0) {
                        $('#' + el.id + '_background').css({
                            zIndex: (zindexvalues[el.id] + 1)
                        });
                    }
                }

                // Add z-index to the wrapper
                if (zindexvalues[el.id] > 0) {
                    $wrapper.css({
                        zIndex: (zindexvalues[el.id] + 2)
                    });
                }
            }

            if (options.detach) {
                $wrapper.prepend(el);
                $el.show();
            } else {
                $wrapper.show();
            }

            opentimer = setTimeout(function() {
                $wrapper.css({
                    visibility: 'visible',
                    opacity: 1
                });

                $('html').addClass('popup_visible').addClass('popup_visible_' + el.id);
                $wrapper.addClass('popup_wrapper_visible');
            }, 20); // 20ms required for opening animation to occur in FF

            // Disable background layer scrolling when popup is opened
            if (options.scrolllock) {
                $body.css('overflow', 'hidden');
                if ($body.height() > $window.height()) {
                    $body.css('margin-right', bodymarginright + scrollbarwidth);
                }
            }

            if(options.backgroundactive){
                //calculates the vertical align
                $el.css({
                    top:(
                        $window.height() - (
                            $el.get(0).offsetHeight +
                            parseInt($el.css('margin-top'), 10) +
                            parseInt($el.css('margin-bottom'), 10)
                            )
                        )/2 +'px'
                });
            }

            $el.css({
                'visibility': 'visible',
                'opacity': 1
            });

            // Show background
            if (options.background) {
                $background.css({
                    'visibility': 'visible',
                    'opacity': options.opacity
                });

                // Fix IE8 issue with background not appearing
                setTimeout(function() {
                    $background.css({
                        'opacity': options.opacity
                    });
                }, 0);
            }

            $el.data('popup-visible', true);

            // Position popup
            methods.reposition(el, ordinal);

            // Remember which element had focus before opening a popup
            $el.data('focusedelementbeforepopup', document.activeElement);

            // Handler: Keep focus inside dialog box
            if (options.keepfocus) {
                // Make holder div focusable
                $el.attr('tabindex', -1);

                // Focus popup or user specified element.
                // Initial timeout of 50ms is set to give some time to popup to show after clicking on
                // `open` element, and after animation is complete to prevent background scrolling.
                setTimeout(function() {
                    if (options.focuselement === 'closebutton') {
                        $('#' + el.id + ' .' + el.id + closesuffix + ':first').focus();
                    } else if (options.focuselement) {
                        $(options.focuselement).focus();
                    } else {
                        $el.focus();
                    }
                }, options.focusdelay);

            }

            // Hide main content from screen readers
            $(options.pagecontainer).attr('aria-hidden', true);

            // Reveal popup content to screen readers
            $el.attr('aria-hidden', false);

            callback(el, ordinal, options.onopen);

            if (transitionsupport) {
                $wrapper.one('transitionend', function() {
                    callback(el, ordinal, options.opentransitionend);
                });
            } else {
                callback(el, ordinal, options.opentransitionend);
            }
        },

        /**
         * Hide method
         *
         * @param {object} el - popup instance DOM node
         */
        hide: function (el) {
            if(opentimer) clearTimeout(opentimer);

            var $body = $('body');
            var $el = $(el);
            var options = $el.data('popupoptions');
            var $wrapper = $('#' + el.id + '_wrapper');
            var $background = $('#' + el.id + '_background');

            $el.data('popup-visible', false);


            if (stack.length === 1) {
                $('html').removeClass('popup_visible').removeClass('popup_visible_' + el.id);
            } else {
                if($('html').hasClass('popup_visible_' + el.id)) {
                    $('html').removeClass('popup_visible_' + el.id);
                }
            }

            // Remove last opened popup from the stack
            stack.pop();

            if($wrapper.hasClass('popup_wrapper_visible')) {
                $wrapper.removeClass('popup_wrapper_visible');
            }

            if (options.keepfocus) {
                // Focus back on saved element
                setTimeout(function() {
                    if ($($el.data('focusedelementbeforepopup')).is(':visible')) {
                        $el.data('focusedelementbeforepopup').focus();
                    }
                }, 0);
            }

            // Hide popup
            $wrapper.css({
                'visibility': 'hidden',
                'opacity': 0
            });
            $el.css({
                'visibility': 'hidden',
                'opacity': 0
            });

            // Hide background
            if (options.background) {
                $background.css({
                    'visibility': 'hidden',
                    'opacity': 0
                });
            }

            // Reveal main content to screen readers
            $(options.pagecontainer).attr('aria-hidden', false);

            // Hide popup content from screen readers
            $el.attr('aria-hidden', true);

            // `onclose` callback event
            callback(el, lastclicked[el.id], options.onclose);

            if (transitionsupport && $el.css('transition-duration') !== '0s') {
                $el.one('transitionend', function(e) {

                    if (!($el.data('popup-visible'))) {
                        if (options.detach) {
                            $el.hide().detach();
                        } else {
                            $wrapper.hide();
                        }
                    }

                    // Re-enable scrolling of background layer
                    if (options.scrolllock) {
                        setTimeout(function() {
                            $body.css({
                                overflow: 'visible',
                                'margin-right': bodymarginright
                            });
                        }, 10); // 10ms added for CSS transition in Firefox which doesn't like overflow:auto
                    }

                    callback(el, lastclicked[el.id], options.closetransitionend);
                });
            } else {
                if (options.detach) {
                    $el.hide().detach();
                } else {
                    $wrapper.hide();
                }

                // Re-enable scrolling of background layer
                if (options.scrolllock) {
                    setTimeout(function() {
                        $body.css({
                            overflow: 'visible',
                            'margin-right': bodymarginright
                        });
                    }, 10); // 10ms added for CSS transition in Firefox which doesn't like overflow:auto
                }

                callback(el, lastclicked[el.id], options.closetransitionend);
            }

        },

        /**
         * Toggle method
         *
         * @param {object} el - popup instance DOM node
         * @param {number} ordinal - order number of an `open` element
         */
        toggle: function (el, ordinal) {
            if ($(el).data('popup-visible')) {
                methods.hide(el);
            } else {
                setTimeout(function() {
                    methods.show(el, ordinal);
                }, 0);
            }
        },

        /**
         * Reposition method
         *
         * @param {object} el - popup instance DOM node
         * @param {number} ordinal - order number of an `open` element
         */
        reposition: function (el, ordinal) {
            var $el = $(el);
            var options = $el.data('popupoptions');
            var $wrapper = $('#' + el.id + '_wrapper');
            var $background = $('#' + el.id + '_background');

            ordinal = ordinal || 0;

            // Tooltip type
            if (options.type == 'tooltip') {
                $wrapper.css({
                    'position': 'absolute'
                });

                var $tooltipanchor;
                if (options.tooltipanchor) {
                    $tooltipanchor = $(options.tooltipanchor);
                } else if (options.openelement) {
                    $tooltipanchor = $(options.openelement).filter('[data-popup-ordinal="' + ordinal + '"]');
                } else {
                    $tooltipanchor = $('.' + el.id + opensuffix + '[data-popup-ordinal="' + ordinal + '"]');
                }

                var linkOffset = $tooltipanchor.offset();

                // Horizontal position for tooltip
                if (options.horizontal == 'right') {
                    $wrapper.css('left', linkOffset.left + $tooltipanchor.outerWidth() + options.offsetleft);
                } else if (options.horizontal == 'leftedge') {
                    $wrapper.css('left', linkOffset.left + $tooltipanchor.outerWidth() - $tooltipanchor.outerWidth() +  options.offsetleft);
                } else if (options.horizontal == 'left') {
                    $wrapper.css('right', $window.width() - linkOffset.left  - options.offsetleft);
                } else if (options.horizontal == 'rightedge') {
                    $wrapper.css('right', $window.width()  - linkOffset.left - $tooltipanchor.outerWidth() - options.offsetleft);
                } else {
                    $wrapper.css('left', linkOffset.left + ($tooltipanchor.outerWidth() / 2) - ($el.outerWidth() / 2) - parseFloat($el.css('marginLeft')) + options.offsetleft);
                }

                // Vertical position for tooltip
                if (options.vertical == 'bottom') {
                    $wrapper.css('top', linkOffset.top + $tooltipanchor.outerHeight() + options.offsettop);
                } else if (options.vertical == 'bottomedge') {
                    $wrapper.css('top', linkOffset.top + $tooltipanchor.outerHeight() - $el.outerHeight() + options.offsettop);
                } else if (options.vertical == 'top') {
                    $wrapper.css('bottom', $window.height() - linkOffset.top - options.offsettop);
                } else if (options.vertical == 'topedge') {
                    $wrapper.css('bottom', $window.height() - linkOffset.top - $el.outerHeight() - options.offsettop);
                } else {
                    $wrapper.css('top', linkOffset.top + ($tooltipanchor.outerHeight() / 2) - ($el.outerHeight() / 2) - parseFloat($el.css('marginTop')) + options.offsettop);
                }

            // Overlay type
            } else if (options.type == 'overlay') {

                // Horizontal position for overlay
                if (options.horizontal) {
                    $wrapper.css('text-align', options.horizontal);
                } else {
                    $wrapper.css('text-align', 'center');
                }

                // Vertical position for overlay
                if (options.vertical) {
                    $el.css('vertical-align', options.vertical);
                } else {
                    $el.css('vertical-align', 'middle');
                }
            }
        },

        /**
         * Add-close-button method
         *
         * @param {object} el - popup instance DOM node
         */
        addclosebutton: function (el) {
            var genericCloseButton;

            if ($(el).data('popupoptions').closebuttonmarkup) {
                genericCloseButton = $(options.closebuttonmarkup).addClass(el.id + '_close');
            } else {
                genericCloseButton = '<button class="popup_close ' + el.id + '_close" title="Close" aria-label="Close"><span aria-hidden="true">×</span></button>';
            }

            if ($el.data('popup-initialized')){
                $el.append(genericCloseButton);
            }

        }

    };

    /**
     * Callback event calls
     *
     * @param {object} el - popup instance DOM node
     * @param {number} ordinal - order number of an `open` element
     * @param {function} func - callback function
     */
    var callback = function (el, ordinal, func) {
        var options = $(el).data('popupoptions');
        var openelement =  (options.openelement) ? options.openelement : ('.' + el.id + opensuffix);
        var elementclicked = $(openelement + '[data-popup-ordinal="' + ordinal + '"]');
        if (typeof func == 'function') {
            func.call($(el), el, elementclicked);
        }
    };

    // Hide popup if ESC key is pressed
    $(document).on('keydown', function (event) {
        if(stack.length) {
            var elementId = stack[stack.length - 1];
            var el = document.getElementById(elementId);

            if ($(el).data('popupoptions').escape && event.keyCode == 27) {
                methods.hide(el);
            }
        }
    });

    // Hide popup on click
    $(document).on('click', function (event) {
        if(stack.length) {
            var elementId = stack[stack.length - 1];
            var el = document.getElementById(elementId);
            var closeButton = ($(el).data('popupoptions').closeelement) ? $(el).data('popupoptions').closeelement : ('.' + el.id + closesuffix);

            // Click on Close button
            if ($(event.target).closest(closeButton).length) {
                event.preventDefault();
                methods.hide(el);
            }

            // Click outside of popup
            if ($(el).data('popupoptions').blur && !$(event.target).closest('#' + elementId).length && event.which !== 2 && $(event.target).is(':visible')) {
                methods.hide(el);

                if ($(el).data('popupoptions').type === 'overlay') {
                    event.preventDefault(); // iOS will trigger click on the links below the overlay when clicked on the overlay if we don't prevent default action
                }
            }
        }
    });

    // Keep keyboard focus inside of popup
    $(document).on('focusin', function(event) {
        if(stack.length) {
            var elementId = stack[stack.length - 1];
            var el = document.getElementById(elementId);

            if ($(el).data('popupoptions').keepfocus) {
                if (!el.contains(event.target)) {
                    event.stopPropagation();
                    el.focus();
                }
            }
        }
    });

    /**
     * Plugin API
     */
    $.fn.popup = function (customoptions) {
        return this.each(function () {

            $el = $(this);

            if (typeof customoptions === 'object') {  // e.g. $('#popup').popup({'color':'blue'})
                var opt = $.extend({}, $.fn.popup.defaults, customoptions);
                $el.data('popupoptions', opt);
                options = $el.data('popupoptions');

                methods._init(this);

            } else if (typeof customoptions === 'string') { // e.g. $('#popup').popup('hide')
                if (!($el.data('popupoptions'))) {
                    $el.data('popupoptions', $.fn.popup.defaults);
                    options = $el.data('popupoptions');
                }

                methods[customoptions].call(this, this);

            } else { // e.g. $('#popup').popup()
                if (!($el.data('popupoptions'))) {
                    $el.data('popupoptions', $.fn.popup.defaults);
                    options = $el.data('popupoptions');
                }

                methods._init(this);

            }

        });
    };

    $.fn.popup.defaults = {
        type: 'overlay',
        autoopen: false,
        background: true,
        backgroundactive: false,
        color: 'black',
        opacity: '0.5',
        horizontal: 'center',
        vertical: 'middle',
        offsettop: 0,
        offsetleft: 0,
        escape: true,
        blur: true,
        setzindex: true,
        autozindex: false,
        scrolllock: false,
        closebutton: false,
        closebuttonmarkup: null,
        keepfocus: true,
        focuselement: null,
        focusdelay: 50,
        outline: false,
        pagecontainer: null,
        detach: false,
        openelement: null,
        closeelement: null,
        transition: null,
        tooltipanchor: null,
        beforeopen: null,
        onclose: null,
        onopen: null,
        opentransitionend: null,
        closetransitionend: null
    };

})(jQuery);




//modernizr.custome.js file start 




;
window.Modernizr=function(a,b,c){
    function w(a){
        j.cssText=a
    }
    function x(a,b){
        return w(m.join(a+";")+(b||""))
    }
    function y(a,b){
        return typeof a===b
    }
    function z(a,b){
        return!!~(""+a).indexOf(b)
    }
    function A(a,b,d){
        for(var e in a){
            var f=b[a[e]];
            if(f!==c)return d===!1?a[e]:y(f,"function")?f.bind(d||b):f
        }
        return!1
    }
    var d="2.6.2",e={},f=!0,g=b.documentElement,h="modernizr",i=b.createElement(h),j=i.style,k,l={}.toString,m=" -webkit- -moz- -o- -ms- ".split(" "),n={},o={},p={},q=[],r=q.slice,s,t=function(a,c,d,e){
        var f,i,j,k,l=b.createElement("div"),m=b.body,n=m||b.createElement("body");
        if(parseInt(d,10))while(d--)j=b.createElement("div"),j.id=e?e[d]:h+(d+1),l.appendChild(j);
        return f=["&#173;",'<style id="s',h,'">',a,"</style>"].join(""),l.id=h,(m?l:n).innerHTML+=f,n.appendChild(l),m||(n.style.background="",n.style.overflow="hidden",k=g.style.overflow,g.style.overflow="hidden",g.appendChild(n)),i=c(l,a),m?l.parentNode.removeChild(l):(n.parentNode.removeChild(n),g.style.overflow=k),!!i
    },u={}.hasOwnProperty,v;
    !y(u,"undefined")&&!y(u.call,"undefined")?v=function(a,b){
        return u.call(a,b)
    }:v=function(a,b){
        return b in a&&y(a.constructor.prototype[b],"undefined")
    },Function.prototype.bind||(Function.prototype.bind=function(b){
        var c=this;
        if(typeof c!="function")throw new TypeError;
        var d=r.call(arguments,1),e=function(){
            if(this instanceof e){
                var a=function(){};
                
                a.prototype=c.prototype;
                var f=new a,g=c.apply(f,d.concat(r.call(arguments)));
                return Object(g)===g?g:f
            }
            return c.apply(b,d.concat(r.call(arguments)))
        };
            
        return e
    }),n.touch=function(){
        var c;
        return"ontouchstart"in a||a.DocumentTouch&&b instanceof DocumentTouch?c=!0:t(["@media (",m.join("touch-enabled),("),h,")","{#modernizr{top:9px;position:absolute}}"].join(""),function(a){
            c=a.offsetTop===9
        }),c
    };
        
    for(var B in n)v(n,B)&&(s=B.toLowerCase(),e[s]=n[B](),q.push((e[s]?"":"no-")+s));return e.addTest=function(a,b){
        if(typeof a=="object")for(var d in a)v(a,d)&&e.addTest(d,a[d]);else{
            a=a.toLowerCase();
            if(e[a]!==c)return e;
            b=typeof b=="function"?b():b,typeof f!="undefined"&&f&&(g.className+=" "+(b?"":"no-")+a),e[a]=b
        }
        return e
    },w(""),i=k=null,function(a,b){
        function k(a,b){
            var c=a.createElement("p"),d=a.getElementsByTagName("head")[0]||a.documentElement;
            return c.innerHTML="x<style>"+b+"</style>",d.insertBefore(c.lastChild,d.firstChild)
        }
        function l(){
            var a=r.elements;
            return typeof a=="string"?a.split(" "):a
        }
        function m(a){
            var b=i[a[g]];
            return b||(b={},h++,a[g]=h,i[h]=b),b
        }
        function n(a,c,f){
            c||(c=b);
            if(j)return c.createElement(a);
            f||(f=m(c));
            var g;
            return f.cache[a]?g=f.cache[a].cloneNode():e.test(a)?g=(f.cache[a]=f.createElem(a)).cloneNode():g=f.createElem(a),g.canHaveChildren&&!d.test(a)?f.frag.appendChild(g):g
        }
        function o(a,c){
            a||(a=b);
            if(j)return a.createDocumentFragment();
            c=c||m(a);
            var d=c.frag.cloneNode(),e=0,f=l(),g=f.length;
            for(;e<g;e++)d.createElement(f[e]);
            return d
        }
        function p(a,b){
            b.cache||(b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag()),a.createElement=function(c){
                return r.shivMethods?n(c,a,b):b.createElem(c)
            },a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+l().join().replace(/\w+/g,function(a){
                return b.createElem(a),b.frag.createElement(a),'c("'+a+'")'
            })+");return n}")(r,b.frag)
        }
        function q(a){
            a||(a=b);
            var c=m(a);
            return r.shivCSS&&!f&&!c.hasCSS&&(c.hasCSS=!!k(a,"article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}")),j||p(a,c),a
        }
        var c=a.html5||{},d=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,e=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,f,g="_html5shiv",h=0,i={},j;
        (function(){
            try{
                var a=b.createElement("a");
                a.innerHTML="<xyz></xyz>",f="hidden"in a,j=a.childNodes.length==1||function(){
                    b.createElement("a");
                    var a=b.createDocumentFragment();
                    return typeof a.cloneNode=="undefined"||typeof a.createDocumentFragment=="undefined"||typeof a.createElement=="undefined"
                }()
            }catch(c){
                f=!0,j=!0
            }
        })();
        var r={
            elements:c.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",
            shivCSS:c.shivCSS!==!1,
            supportsUnknownElements:j,
            shivMethods:c.shivMethods!==!1,
            type:"default",
            shivDocument:q,
            createElement:n,
            createDocumentFragment:o
        };
    
        a.html5=r,q(b)
    }(this,b),e._version=d,e._prefixes=m,e.testStyles=t,g.className=g.className.replace(/(^|\s)no-js(\s|$)/,"$1$2")+(f?" js "+q.join(" "):""),e
}(this,this.document),function(a,b,c){
    function d(a){
        return"[object Function]"==o.call(a)
    }
    function e(a){
        return"string"==typeof a
    }
    function f(){}
    function g(a){
        return!a||"loaded"==a||"complete"==a||"uninitialized"==a
    }
    function h(){
        var a=p.shift();
        q=1,a?a.t?m(function(){
            ("c"==a.t?B.injectCss:B.injectJs)(a.s,0,a.a,a.x,a.e,1)
        },0):(a(),h()):q=0
    }
    function i(a,c,d,e,f,i,j){
        function k(b){
            if(!o&&g(l.readyState)&&(u.r=o=1,!q&&h(),l.onload=l.onreadystatechange=null,b)){
                "img"!=a&&m(function(){
                    t.removeChild(l)
                },50);
                for(var d in y[c])y[c].hasOwnProperty(d)&&y[c][d].onload()
            }
        }
        var j=j||B.errorTimeout,l=b.createElement(a),o=0,r=0,u={
            t:d,
            s:c,
            e:f,
            a:i,
            x:j
        };
    
        1===y[c]&&(r=1,y[c]=[]),"object"==a?l.data=c:(l.src=c,l.type=a),l.width=l.height="0",l.onerror=l.onload=l.onreadystatechange=function(){
            k.call(this,r)
        },p.splice(e,0,u),"img"!=a&&(r||2===y[c]?(t.insertBefore(l,s?null:n),m(k,j)):y[c].push(l))
    }
    function j(a,b,c,d,f){
        return q=0,b=b||"j",e(a)?i("c"==b?v:u,a,b,this.i++,c,d,f):(p.splice(this.i++,0,a),1==p.length&&h()),this
    }
    function k(){
        var a=B;
        return a.loader={
            load:j,
            i:0
        },a
    }
    var l=b.documentElement,m=a.setTimeout,n=b.getElementsByTagName("script")[0],o={}.toString,p=[],q=0,r="MozAppearance"in l.style,s=r&&!!b.createRange().compareNode,t=s?l:n.parentNode,l=a.opera&&"[object Opera]"==o.call(a.opera),l=!!b.attachEvent&&!l,u=r?"object":l?"script":"img",v=l?"script":u,w=Array.isArray||function(a){
        return"[object Array]"==o.call(a)
    },x=[],y={},z={
        timeout:function(a,b){
            return b.length&&(a.timeout=b[0]),a
        }
    },A,B;
    B=function(a){
        function b(a){
            var a=a.split("!"),b=x.length,c=a.pop(),d=a.length,c={
                url:c,
                origUrl:c,
                prefixes:a
            },e,f,g;
            for(f=0;f<d;f++)g=a[f].split("="),(e=z[g.shift()])&&(c=e(c,g));
            for(f=0;f<b;f++)c=x[f](c);
            return c
        }
        function g(a,e,f,g,h){
            var i=b(a),j=i.autoCallback;
            i.url.split(".").pop().split("?").shift(),i.bypass||(e&&(e=d(e)?e:e[a]||e[g]||e[a.split("/").pop().split("?")[0]]),i.instead?i.instead(a,e,f,g,h):(y[i.url]?i.noexec=!0:y[i.url]=1,f.load(i.url,i.forceCSS||!i.forceJS&&"css"==i.url.split(".").pop().split("?").shift()?"c":c,i.noexec,i.attrs,i.timeout),(d(e)||d(j))&&f.load(function(){
                k(),e&&e(i.origUrl,h,g),j&&j(i.origUrl,h,g),y[i.url]=2
            })))
        }
        function h(a,b){
            function c(a,c){
                if(a){
                    if(e(a))c||(j=function(){
                        var a=[].slice.call(arguments);
                        k.apply(this,a),l()
                    }),g(a,j,b,0,h);
                    else if(Object(a)===a)for(n in m=function(){
                        var b=0,c;
                        for(c in a)a.hasOwnProperty(c)&&b++;return b
                    }(),a)a.hasOwnProperty(n)&&(!c&&!--m&&(d(j)?j=function(){
                        var a=[].slice.call(arguments);
                        k.apply(this,a),l()
                    }:j[n]=function(a){
                        return function(){
                            var b=[].slice.call(arguments);
                            a&&a.apply(this,b),l()
                        }
                    }(k[n])),g(a[n],j,b,n,h))
                }else!c&&l()
            }
            var h=!!a.test,i=a.load||a.both,j=a.callback||f,k=j,l=a.complete||f,m,n;
            c(h?a.yep:a.nope,!!i),i&&c(i)
        }
        var i,j,l=this.yepnope.loader;
        if(e(a))g(a,0,l,0);
        else if(w(a))for(i=0;i<a.length;i++)j=a[i],e(j)?g(j,0,l,0):w(j)?B(j):Object(j)===j&&h(j,l);else Object(a)===a&&h(a,l)
    },B.addPrefix=function(a,b){
        z[a]=b
    },B.addFilter=function(a){
        x.push(a)
    },B.errorTimeout=1e4,null==b.readyState&&b.addEventListener&&(b.readyState="loading",b.addEventListener("DOMContentLoaded",A=function(){
        b.removeEventListener("DOMContentLoaded",A,0),b.readyState="complete"
    },0)),a.yepnope=k(),a.yepnope.executeStack=h,a.yepnope.injectJs=function(a,c,d,e,i,j){
        var k=b.createElement("script"),l,o,e=e||B.errorTimeout;
        k.src=a;
        for(o in d)k.setAttribute(o,d[o]);c=j?h:c||f,k.onreadystatechange=k.onload=function(){
            !l&&g(k.readyState)&&(l=1,c(),k.onload=k.onreadystatechange=null)
        },m(function(){
            l||(l=1,c(1))
        },e),i?k.onload():n.parentNode.insertBefore(k,n)
    },a.yepnope.injectCss=function(a,c,d,e,g,i){
        var e=b.createElement("link"),j,c=i?h:c||f;
        e.href=a,e.rel="stylesheet",e.type="text/css";
        for(j in d)e.setAttribute(j,d[j]);g||(n.parentNode.insertBefore(e,n),m(c,0))
    }
}(this,document),Modernizr.load=function(){
    yepnope.apply(window,[].slice.call(arguments,0))
};





// amaar content start from here




// Load the Visualization API and the piechart package.
google.load('visualization', '1.1', {
    'packages': ['corechart']
});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(function () {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Letter');
    data.addColumn('number', 'Number');
    data.addRows([
        ['A', 3],
        ['B', 1],
        ['C', 5],
        ['D', 4],
        ['E', 2]
        ]);

   
    var options = {
    // width: 370,
    //height:300,

    //title: 'Barchart Example '
    // animation: {
    //duration: 1000,
    //easing: 'linear'
    //vAxis: {
    //viewWindow: {
    //max: 8
    }
    //}
    // };

    var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
    drawChart();

    function drawChart() {
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
    }


    $(document).ready(function () {
        $(window).resize(function () {
            drawChart();
        });
    });
});


// line chart code start


google.setOnLoadCallback(function () {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Letter');
    data.addColumn('number', 'Number');
    data.addRows([
        ['A', 2],
        ['B', 4],
        ['C', 1],
        ['D', 5],
        ['E', 2]
        ]);

    // Set chart options
    var options = {
        backgroundColor:'#FFCC66'

    //width: 814,
    //height:300,

    //title: 'Line Chart Example'
    //animation: {
    //duration: 1000,
    //easing: 'linear'
    }
    // }

    var chart = new google.visualization.LineChart(document.getElementById('Linechart'));
    drawChart();

    function drawChart() {
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
    }


    $(document).ready(function () {
        $(window).resize(function () {
            drawChart();
        });
    });
});


//pie chart code start

google.setOnLoadCallback(function () {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Letter');
    data.addColumn('number', 'Number');
    data.addRows([
        ['A', 3],
        ['B', 3],
        ['C', 5],
        ['D', 4],
        ['E', 4]
        ]);

    // Set chart options
    var options = {
    //backgroundColor:'#FF9933',
    //is3D: true,
    //width: 370,
    //height:300,
    //title: 'Pie Chart Example'
    //animation: {
    //duration: 500,
    //easing: 'linear'
    }
    //};

    var chart = new google.visualization.PieChart(document.getElementById('Piechart'));
    drawChart();

    function drawChart() {
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
    }


    $(document).ready(function () {
        $(window).resize(function () {
            drawChart();
        });
    });
});



