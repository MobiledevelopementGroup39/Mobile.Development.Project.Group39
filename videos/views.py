from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.decorators import login_required
from .models import Video
from .forms import VideoCommentForm, VideoUploadForm

@login_required
def like_video(request, video_id):
    video = get_object_or_404(Video, id=video_id)
    if request.user in video.likes.all():
        video.likes.remove(request.user)
    else:
        video.likes.add(request.user)
        video.dislikes.remove(request.user)  # Remove dislike if user liked
    return redirect('video_list')

@login_required
def dislike_video(request, video_id):
    video = get_object_or_404(Video, id=video_id)
    if request.user in video.dislikes.all():
        video.dislikes.remove(request.user)
    else:
        video.dislikes.add(request.user)
        video.likes.remove(request.user)  # Remove like if user disliked
    return redirect('video_list')

@login_required
def add_video_comment(request, video_id):
    video = get_object_or_404(Video, id=video_id)
    if request.method == 'POST':
        form = VideoCommentForm(request.POST)
        if form.is_valid():
            comment = form.save(commit=False)
            comment.video = video
            comment.user = request.user
            comment.save()
            return redirect('video_list')
    return redirect('video_list')

def video_list(request):
    videos = Video.objects.all()
    return render(request, 'video_list.html', {'videos': videos})

@login_required
def upload_video(request):
    if request.method == 'POST':
        form = VideoUploadForm(request.POST, request.FILES)
        if form.is_valid():
            video = form.save(commit=False)
            video.uploaded_by = request.user
            video.save()
            return redirect('video_list')
    else:
        form = VideoUploadForm()
    return render(request, 'upload_video.html', {'form': form})
