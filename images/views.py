from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.decorators import login_required
from .models import ImagePost
from .forms import ImageUploadForm,ImageCommentForm

def image_list(request):
    images = ImagePost.objects.all()
    return render(request, 'image_list.html', {'images': images})

@login_required
def upload_image(request):
    if request.method == 'POST':
        form = ImageUploadForm(request.POST, request.FILES)
        if form.is_valid():
            image_post = form.save(commit=False)
            image_post.uploaded_by = request.user
            image_post.save()
            return redirect('image_list')
    else:
        form = ImageUploadForm()
    return render(request, 'upload_image.html', {'form': form})

@login_required
def add_image_comment(request, image_id):
    image = get_object_or_404(ImagePost, id=image_id)
    if request.method == 'POST':
        form = ImageCommentForm(request.POST)
        if form.is_valid():
            comment = form.save(commit=False)
            comment.image = image
            comment.user = request.user
            comment.save()
            return redirect('image_list')
    return redirect('image_list')

@login_required
def like_image(request, image_id):
    image = get_object_or_404(ImagePost, id=image_id)
    if request.user in image.likes.all():
        image.likes.remove(request.user)
    else:
        image.likes.add(request.user)
        image.dislikes.remove(request.user)  # Remove dislike if user liked
    return redirect('image_list')

@login_required
def dislike_image(request, image_id):
    image = get_object_or_404(ImagePost, id=image_id)
    if request.user in image.dislikes.all():
        image.dislikes.remove(request.user)
    else:
        image.dislikes.add(request.user)
        image.likes.remove(request.user)  # Remove like if user disliked
    return redirect('image_list')
